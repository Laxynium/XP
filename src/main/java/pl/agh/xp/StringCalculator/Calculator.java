package pl.agh.xp.StringCalculator;

import io.vavr.control.Either;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calculator {
    public Either<String, String> add(String input) {
        var separator = "[,\\n]";
        if (input.startsWith("//")) {
            String[] split = input.split("\n", 2);
            var beforeFirstLine = split[0];
            separator = beforeFirstLine.replace("//", "");
            input = split[1];
        }

        if (input == null || input.isBlank())
            return Either.right("0");
        var split = input.split(separator, -1);
        Supplier<Stream<Either<String, Float>>> parsedNumbers = () -> Arrays.stream(split)
                .map(this::tryParseFloat);
        if (parsedNumbers.get().anyMatch(Either::isLeft)) {
            return Either.left("One of the numbers is invalid.");
        }

        var negativeNumbers = parsedNumbers.get()
                .map(Either::get)
                .filter(x -> x < .0f)
                .collect(Collectors.toList());
        if (!negativeNumbers.isEmpty()) {
            var joined = negativeNumbers.stream().map(x -> new DecimalFormat("0.#")
                    .format(x))
                    .collect(Collectors.joining(", "));
            return Either.left("Negative not allowed : " + joined);
        }

        var sum = parsedNumbers.get().map(Either::get).reduce(0f, Float::sum);

        return Either.right(new DecimalFormat("0.#").format(sum));
    }

    private Either<String, Float> tryParseFloat(String input) {
        try {
            float value = Float.parseFloat(input);
            return Either.right(value);
        } catch (NumberFormatException e) {
            return Either.left(e.getMessage());
        }
    }
}
