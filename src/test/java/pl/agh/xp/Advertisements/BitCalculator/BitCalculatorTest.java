import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BitCalculatorTest {

    private BitCalculator sut;

    @BeforeEach
    void setup() {
        sut = new BitCalculator();
    }

    @ParameterizedTest
    @CsvSource({"'',0", "6,2", "254,7"})
    void Add_NoOfBits1ForSingleNumber_WhenNumberIsValid(String input, String expected) {
        int expectedInt = Integer.parseInt(expected);
        int result = sut.noOfBits1(input);
        Assertions.assertEquals(expectedInt, result);
    }

    @ParameterizedTest
    @ValueSource(strings={"-1", "256"})
    void Add_NoOfBits1ForSingleNumber_WhenNumberIsOutOfRange(String input) {
        Assertions.assertThrows(Exception.class, () -> sut.noOfBits1(input), "Number out of range");
    }

    @ParameterizedTest
    @CsvSource(value = {"0;1;5:3", "27;200:7"}, delimiter = ':')
    void Add_NoOfBits1ForFewNumbersOneDelimiter_WhenNumbersAreValid(String inputs, String expected) {
        int expectedInt = Integer.parseInt(expected);
        int result = sut.noOfBits1(inputs);
        Assertions.assertEquals(expectedInt, result);
    }

    @ParameterizedTest
    @CsvSource(value = {"0 1 5:3", "27 200:7", "55;149:9"}, delimiter = ':')
    void Add_NoOfBits1ForFewNumbersTwoDelimiters_WhenNumbersAreValid(String inputs, String expected) {
        int expectedInt = Integer.parseInt(expected);
        int result = sut.noOfBits1(inputs);
        Assertions.assertEquals(expectedInt, result);
    }

    @ParameterizedTest
    @CsvSource(value = {"0 1 5:3", "27  200:7", "55;149:9", "1  6:3"}, delimiter = ':')
    void Add_NoOfBits1ForFewNumbersWhiteCharsDelimiters_WhenNumbersAreValid(String inputs, String expected) {
        int expectedInt = Integer.parseInt(expected);
        int result = sut.noOfBits1(inputs);
        Assertions.assertEquals(expectedInt, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0 1 ,5", "27/200"})
    void Add_NoOfBits1ForFewNumbersWhiteCharsDelimiters_WhenStringIsNotValid(String inputs) {
        Assertions.assertThrows(Exception.class, () -> sut.noOfBits1(inputs), "Wrong format");
    }

    @ParameterizedTest
    @CsvSource(value = {"0 1 $5:3", "$C7 $F4:10"}, delimiter = ':')
    void Add_NoOfBits1ForFewNumbersDecAndHexDelimiters_WhenNumbersAreValid(String inputs, String expected) {
        int expectedInt = Integer.parseInt(expected);
        int result = sut.noOfBits1(inputs);
        Assertions.assertEquals(expectedInt, result);
    }

}
