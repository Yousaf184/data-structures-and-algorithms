import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        List<Integer> points = new ArrayList<>();
        int currentMinSegmentEnd;
        // sort segments based on their end points
        Arrays.sort(segments);
        // add first segment's end point in the points list
        // segment with the minimum end point will be included in all cases
        points.add(segments[0].end);

        currentMinSegmentEnd = segments[0].end;

        for (int i = 1; i < segments.length; i++) {
            // if current segment's start point is greater than currentMinSegmentEnd
            if (segments[i].start > currentMinSegmentEnd) {
                currentMinSegmentEnd = segments[i].end;
                points.add(segments[i].end);
            }
        }

        return convertToIntArray(points);
    }

    private static int[] convertToIntArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        int index = 0;
        for (int i : list) {
            arr[index] = i;
            index++;
        }

        return arr;
    }

    private static class Segment implements Comparable<Segment> {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment o) {
            if (this.end < o.end) return -1;
            else if (this.end > o.end) return 1;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }

        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}