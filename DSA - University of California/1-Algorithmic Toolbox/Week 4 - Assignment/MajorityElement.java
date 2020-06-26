import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        // if 'a' is of length 1 or there is only 1 element in the array
        // return -1 because there is no majority element in this case
        if (left == right || a.length <= 1) {
            return -1;
        }

        // if there are 2 elements in the array,
        // return the element on first index
        // it doesn't matters which number is returned because there
        // is no majority element in this case
        if (left + 1 == right) {
            return a[left];
        }

        // divide the array
        int mid = (left + right) / 2;

        int leftNum = getMajorityElement(a, left, mid);
        int rightNum = getMajorityElement(a, mid + 1, right);

        int leftCount = 0;
        int rightCount = 0;

        // find the number of times both leftNum and rightNum
        // exist in the array
        for (int i = left; i < right; i++) {
            if (a[i] == leftNum) leftCount++;
            else if (a[i] == rightNum) rightCount++;
        }

        // if either leftNum or rightNum exists more than half of array's
        // length times, return 1
        if (leftCount > ((left+ right) / 2)) return 1;
        else if (rightCount > ((left + right) / 2)) return 1;

        // if neither of leftNum or rightNum exists more than half of
        // array's length times, return leftNum if exists more times than
        // rightNum otherwise return rightNum
        // This is done to avoid returning -1 if we are in one of the recursive calls
        if (left > 0) {
            return leftCount > rightCount ? leftNum : rightNum;
        }

        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

