package pl.edu.agh.xp.advertisements.menu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.edu.agh.xp.advertisements.auth.AuthContext;
import pl.edu.agh.xp.advertisements.model.LoggedInUser;
import pl.edu.agh.xp.advertisements.model.UserType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MenuFactoryTest {

    @ParameterizedTest
    @MethodSource("loggedInUserToMenu")
    void should_returnProperMenu_whenCorrectUserLoggedIn(LoggedInUser loggedInUser, Class<? extends Menu> menuClass) {
        // given
        AuthContext.setLoggedInUser(loggedInUser);

        // when
        var menu = MenuFactory.create();

        // then
        assertEquals(menuClass, menu.getClass());
    }

    @Test
    void should_throwException_whenUserNotLoggedIn() {
        // given
        AuthContext.setLoggedInUser(null);

        // when
        var exception = assertThrows(RuntimeException.class, MenuFactory::create);

        // then
        assertEquals("User not logged in", exception.getMessage());
    }

    @Test
    void should_throwException_whenUnsupportedUserType() {
        // given
        var loggedInUser = LoggedInUser.builder()
                .username("user")
                .userType(UserType.FAILING_CASE)
                .build();
        AuthContext.setLoggedInUser(loggedInUser);

        // when
        var exception = assertThrows(RuntimeException.class, MenuFactory::create);

        // then
        assertEquals("Unsupported user type", exception.getMessage());
    }

    private static Stream<Arguments> loggedInUserToMenu() {
        return Stream.of(
                Arguments.arguments(
                        LoggedInUser.builder()
                                .username("admin")
                                .userType(UserType.ADMIN)
                                .build(),
                        AdminMenu.class
                ),
                Arguments.arguments(
                        LoggedInUser.builder()
                                .username("adowner")
                                .userType(UserType.AD_OWNER)
                                .build(),
                        AdOwnerMenu.class
                ),
                Arguments.arguments(
                        LoggedInUser.builder()
                                .username("adpublisher")
                                .userType(UserType.AD_PUBLISHER)
                                .build(),
                        AdPublisherMenu.class
                )
        );
    }

}