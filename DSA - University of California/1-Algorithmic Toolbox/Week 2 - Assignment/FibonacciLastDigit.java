import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigit(int n) {
        if (n <= 1) return n;

        int previous = 0;
        int current  = 1;
        int tmp_previous;

        for (int i = 0; i < n - 1; ++i) {
            tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
        }

        return current;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}

