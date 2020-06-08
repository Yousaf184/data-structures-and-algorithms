import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        StringBuilder result = new StringBuilder();

        Arrays.sort(a, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String s1s2 = s1 + s2;
                String s2s1 = s2 + s1;

                // if s1s2 < s2s1 then s2 should be placed before s1
                if (s1s2.compareTo(s2s1) < 0) return 1;
                // if s1s2 > s2s1 then s1 should be placed before s2
                if (s1s2.compareTo(s2s1) > 0) return -1;

                return 0;
            }
        });

        System.out.println(Arrays.toString(a));

        for (String s : a) {
            result.append(s);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }

        System.out.println(largestNumber(a));
    }
}

