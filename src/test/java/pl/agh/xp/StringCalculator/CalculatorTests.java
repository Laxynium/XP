package pl.agh.xp.StringCalculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTests {

    private Calculator calculator = new Calculator();

    @ParameterizedTest
    @MethodSource("simpleTestData")
    public void correctly_adds_int_numbers_separated_by_comma_when_input_string_is_valid(String input, String output){
        var result = calculator.add(input);

        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo(output);
    }

    @ParameterizedTest
    @MethodSource("anyTestData")
    public void correctly_add_any_int_numbers_when_input_string_is_valid(String input, String output) {
        var result = calculator.add(input);

        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo(output);
    }

    @ParameterizedTest
    @MethodSource("floatTestData")
    public void correctly_add_any_float_numbers_when_input_string_is_valid(String input, String output){
        var result = calculator.add(input);

        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo(output);
    }

    @Test
    public void correctly_add_numbers_when_they_are_separated_by_newline(){
        var result = calculator.add("2\n3");

        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo("5");
    }

    @Test
    public void correctly_add_numbers_when_custom_separator_is_used(){
        var result = calculator.add("//;\n1;2;3");

        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo("6");
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

    private static Stream<Arguments> floatTestData(){
        return Stream.of(
                Arguments.arguments("1.1,2.2", "3.3"),
                Arguments.arguments("10.9,20.1,30", "61")
        );
    }
}
