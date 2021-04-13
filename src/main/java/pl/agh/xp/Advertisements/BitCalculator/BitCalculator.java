import java.util.ArrayList;

class BitCalculator {

    int noOfBits1(String numbers) {
        String binaryNumber;
        int counter = 0;
        String delimiters =  "\\s+|;";

        if(numbers.isEmpty()) return 0;

        String[] splitNumbers = numbers.split(delimiters);
        int[] intNumbers = numbersToIntegers(splitNumbers);

        for (int number: intNumbers) {
            if (!(number >= 0 && number <= 255)) {
                throw new RuntimeException("Number out of range");
            }
            binaryNumber = Integer.toBinaryString(number);
            counter += (int)binaryNumber.chars().filter( c -> c=='1').count();
        }

        return counter;

    }

    private int[] numbersToIntegers (String[] stringNumber) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (String number : stringNumber) {
            if (number.charAt(0) == '$') {
                ints.add(Integer.parseInt(number.substring(1), 16));
            } else {
                ints.add(Integer.parseInt(number));
            }
        }
        return ints.stream().mapToInt(i -> i).toArray();
    }
}
