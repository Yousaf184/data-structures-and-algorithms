import java.math.BigInteger;
import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHuge(long n, long m) {
        BigInteger previousFibNum = BigInteger.valueOf(0);
        BigInteger currentFibNum = BigInteger.valueOf(1);
        BigInteger previousTemp;

        int paisanoPeriodLength = getPaisanoPeriodLength(m);
        long modulo = n % paisanoPeriodLength;

        // if modulo = 0, F(0) = 0
        // so currentFibNum should be 0
        // so F(0) % m is calculated instead of F(1) mod m
        if (modulo == 0) {
            currentFibNum = BigInteger.valueOf(0);
        }

        for (int i=1; i<modulo; i++) {
            previousTemp = previousFibNum;
            previousFibNum = currentFibNum;
            currentFibNum = currentFibNum.add(previousTemp);
        }

        return currentFibNum.mod(BigInteger.valueOf(m)).longValueExact();
    }

    private static int getPaisanoPeriodLength(long m) {
        BigInteger previousFibNum = BigInteger.valueOf(0);
        BigInteger currentFibNum = BigInteger.valueOf(1);
        BigInteger nextFibNum = previousFibNum.add(currentFibNum);
        BigInteger mBig = BigInteger.valueOf(m);
        long modulo;
        int periodLength = 0;

        for (int i=1; i<m*m; i++) {
            modulo = currentFibNum.mod(mBig).longValueExact();

            if (modulo == 0 && nextFibNum.mod(mBig).longValueExact() == 1) {
                periodLength = i;
                break;
            }

            previousFibNum = currentFibNum;
            currentFibNum = nextFibNum;
            nextFibNum = previousFibNum.add(currentFibNum);
        }

        return periodLength;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHuge(n, m));
    }
}

/**
 *
 *      m=2
 *      i=1 -> 3
 *
 *      0, 1, 1, 2, 3, 5, 8, 13
 *
 *      i=1 => p=0, c=1, n=1 | p=1, c=1
 *      i=2 => p=1, c=1, n=0 | p=1, c=0
 *      i=3 => p=1, c=0, n=1 | p=0, c=1
 */