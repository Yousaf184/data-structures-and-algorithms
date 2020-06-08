import java.math.BigInteger;
import java.util.*;

public class FibonacciSumSquares {

    // sum of squares of F(0) to F(n) = F(n) * F(n+1)
    private static long getFibonacciSumSquares(long n) {
        if (n <= 1) return n;

        long previousFibNum = 0;
        long currentFibNum = 1;
        long previousTemp;
        BigInteger nthFibNum = BigInteger.valueOf(0);
        BigInteger nPlusOneFibNum;

        final int pisanoLengthFor10 = 60;
        // calculate F(n+1).
        // F(n)' will be calculated in 2nd last iteration of the loop
        long modulo = (n+1) % pisanoLengthFor10;

        for (int i=1; i<modulo; i++) {
            previousTemp = previousFibNum;
            previousFibNum = currentFibNum;
            currentFibNum = currentFibNum + previousTemp;

            // F(n)
            // when i=1, F(2) is calculated.
            // when i = modulo-1, F(n+1) will be calculated.
            // so when i+1 = modulo-1, F(n) will be calculated.
            //
            // in other words, i+1 = modulo-1 will be true
            // in second last iteration of the for loop.
            if (i+1 == modulo-1) {
                nthFibNum = BigInteger.valueOf(currentFibNum);
            }
        }

        nPlusOneFibNum = BigInteger.valueOf(currentFibNum);

        BigInteger product = nthFibNum.multiply(nPlusOneFibNum);

        // sum of squares of F(0) to F(n) = F(n) * F(n+1)
        // %10 to return only last digit
        return product.mod(BigInteger.valueOf(10)).longValueExact();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long k = getFibonacciSumSquares(n);
        System.out.println(k);
    }
}

