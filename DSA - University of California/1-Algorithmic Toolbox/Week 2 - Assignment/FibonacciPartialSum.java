import java.util.*;

public class FibonacciPartialSum {

    // partial sum for (from, to) = F(to + 2) - F(from+1)
    private static long getFibonacciPartialSum(long from, long to) {
        long previousFibNum = 0;
        long currentFibNum = 1;
        long previousTemp;
        long fromPlus1FibNum = 0;  // F(from+1)
        long toPlusTwoFibNum;  // F(to+2)

        // if 'from' = 0, F(from+1) = 1
        // so that F(to + 2) - F(from+1) is calculated correctly
        if (from == 0) {
            fromPlus1FibNum = 1;
        }

        final int paisanoPeriodLengthFor10 = 60;
        // F(to + 2)
        long modulo = (to + 2) % paisanoPeriodLengthFor10;
        // F(from + 1)
        long modulo2 = (from + 1) % paisanoPeriodLengthFor10;

        for (int i=1; i<modulo; i++) {
            previousTemp = previousFibNum;
            previousFibNum = currentFibNum;
            currentFibNum = currentFibNum + previousTemp;

            // F(from + 1)
            // when i = 1, 2nd fibonacci number is calculated
            // so when i+1 = modulo2, F(from+1) will be calculated
            if ((i+1 == modulo2)) {
                fromPlus1FibNum = currentFibNum;
            }
        }

        toPlusTwoFibNum = currentFibNum;

        if (modulo < modulo2) {
            previousFibNum = 0;
            currentFibNum = 1;

            for (int i=1; i<modulo2; i++) {
                previousTemp = previousFibNum;
                previousFibNum = currentFibNum;
                currentFibNum = currentFibNum + previousTemp;
            }

            fromPlus1FibNum = currentFibNum;
        }

        // partial sum for (from, to) = F(to + 2) - F(from+1)
        // f(to + 2) = toPlusTwoFibNum
        // f(from + 1) = fromPlus1FibNum
        toPlusTwoFibNum = toPlusTwoFibNum - fromPlus1FibNum;
        // if 'toPlusTwoFibNum' is negative after subtraction,
        // (toPlusTwoFibNum % 10) will be negative because '%'
        // operator in java returns remainder instead of modulus.
        // modulus and remainder are same for positive values but
        // in case of negative numbers, modulus will be positive
        // number whereas remainder will be negative.
        // example: for -3521994 % 10, modulus = 6, remainder = -4
        //
        // to make negative remainder positive, add 10 to the result
        // and then take its mod with 10
        return  (toPlusTwoFibNum % 10 + 10) % 10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));
    }
}

