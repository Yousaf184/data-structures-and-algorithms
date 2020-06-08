import java.util.*;

public class LCM {
    private static long lcm(int a, int b) {
        return ((long) a * b / gcd_naive(a, b));
    }

    private static int gcd_naive(int a, int b) {
        if (b == 0) return a;
        int aPrime = a % b;
        return gcd_naive(b, aPrime);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(lcm(a, b));
    }
}
