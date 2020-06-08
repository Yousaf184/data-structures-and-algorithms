import java.util.*;

public class FibonacciSumLastDigit {

    // sum of nth fibonacci number = f(n+2) - 1
    private static long getFibonacciSumLastDigit(long n) {
        if (n <= 1) return n;

        long previousFibNum = 0;
        long currentFibNum = 1;
        long previousTemp;

        // last digits of fibnonacci numbers occur
        // in periodic sequences of length 60 since paisano
        // period for m=10 is 60.
        // paisano period is used to restrict number
        // of loop iterations for finding nth fibonacci number
        final int paisanoPeriodLengthFor10 = 60;
        // since sum of nth fibonacci number is f(n+2) - 1
        // we take modulus of n+2 with paisano period
        long modulo = (n+2) % paisanoPeriodLengthFor10;

        for (int i=1; i<modulo; i++) {
            previousTemp = previousFibNum;
            previousFibNum = currentFibNum;
            currentFibNum = currentFibNum + previousTemp;
        }

        // sum(n) = F(n+2) - 1
        // currentFibNum = F(n+2)
        // sum(n) = currentFibNum - 1
        return (currentFibNum - 1) % 10; // %10 to return last digit of sum
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumLastDigit(n);
        System.out.println(s);
    }
}