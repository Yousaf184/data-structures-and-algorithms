import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;

        if (left + 1 >= right) {
            return numberOfInversions;
        }

        int mid = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, mid);
        numberOfInversions += getNumberOfInversions(a, b, mid, right);

        numberOfInversions += merge(a, b, left, mid, right);

        return numberOfInversions;
    }

    private static long merge(int[] a, int[] b, int left, int mid, int right) {
        int inversionCount = 0;

        int leftArrIndex = left;   // starting index of left sub-array
        int rightArrIndex = mid;   // starting index of right sub-array
        int mergedArrIndex = left; // starting index of merged array

        while (leftArrIndex < mid && rightArrIndex < right) {
            if (a[leftArrIndex] <= a[rightArrIndex]) {
                b[mergedArrIndex++] = a[leftArrIndex++];
            } else {
                b[mergedArrIndex++] = a[rightArrIndex++];
                inversionCount += (mid - leftArrIndex);
            }
        }

        // copy remaining elements of left sub-array
        while(leftArrIndex < mid) {
            b[mergedArrIndex++] = a[leftArrIndex++];
        }

        // copy remaining elements of right sub-array
        while(rightArrIndex < right) {
            b[mergedArrIndex++] = a[rightArrIndex++];
        }

        // copy sorted sub-array into original array
        for (int i = left; i < right; i++) {
            a[i] = b[i];
        }

        return inversionCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];

        System.out.println(getNumberOfInversions(a, b, 0, a.length));
    }
}

