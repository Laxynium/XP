package pl.agh.xp.StringCalculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTests {

    @ParameterizedTest
    @MethodSource("simpleTestData")
    public void correctly_adds_numbers_after_comma_when_input_string_is_valid(String input, String output){
        var calculator = new Calculator();

        var result = calculator.add(input);

        assertThat(result).isEqualTo(output);
    }

    @ParameterizedTest
    @MethodSource("anyTestData")
    public void correctly_add_any_numbers_when_input_string_is_valid(String input, String output)
    {
        var calculator = new Calculator();

        var result = calculator.add(input);

        assertThat(result).isEqualTo(output);
    }

    private static Stream<Arguments> simpleTestData(){
        return Stream.of(
                Arguments.arguments("","0"),
                Arguments.arguments("1","1"),
                Arguments.arguments("1,1","2")
        );
    }
    private static Stream<Arguments> anyTestData(){
        return Stream.of(
                Arguments.arguments("1,2,3","6"),
                Arguments.arguments("10,20,30,40","100")
        );
    }

}
