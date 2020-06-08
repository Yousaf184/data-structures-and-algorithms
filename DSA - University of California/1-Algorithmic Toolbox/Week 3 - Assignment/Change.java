import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        int[] denominations = {10, 5, 1};
        int numberOfCoins = 0;

        for (int d : denominations) {
            if (d > m) {
                continue;
            }

            numberOfCoins += m / d;
            m %= d;
        }

        return numberOfCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

