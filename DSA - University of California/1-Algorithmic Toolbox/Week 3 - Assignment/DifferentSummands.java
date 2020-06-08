import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<>();

        // as 2 cannot be divided in to two different numbers that add up to 2
        if (n == 2) {
            summands.add(n);
            return summands;
        }

        double remaining = n;
        double total = 0;

        int i = 1;
        while (total < n) {
            if (i < remaining) {
                summands.add(i);
                total += i;
                remaining -= i;
            } else if (i > remaining && summands.size() > 1) {
                int removed = summands.remove(summands.size() - 1);
                remaining += removed;
                total -= removed;
                summands.add(i);
                total += i;
                remaining -= i;
            } else if (i == remaining) {
                summands.add(i);
                break;
            }

            i++;
        }

        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}