package pl.edu.agh.xp.advertisements.auth;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthenticationServiceFactoryTest {

    @Test
    void authenticationServiceFactoryMethodTest() {
        // given

        // when
        var service = AuthenticationServiceFactory.create();

        // then
        assertTrue(service instanceof UsersFileAuthenticationService);
    }

}