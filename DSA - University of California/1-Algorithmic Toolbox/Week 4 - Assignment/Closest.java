import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }
    }

    static List<Point> getFilteredList(List<Point> list, int left, int right, double midLine, double currentMin) {
        List<Point> filteredList = new ArrayList<>();

        for (int i = left; i < right; i++) {
            if (Math.abs(list.get(i).x - midLine) <= currentMin) {
                filteredList.add(list.get(i));
            }
        }

        return filteredList;
    }

    static double computeMinDistance(List<Point> list, int left, int right) {
        double minDistance = -1;
        double distance;

        for (int i = left; i < right; i++) {
            for (int j = i + 1; j < right; j++) {
                distance = calculateDistance(
                    list.get(i).x, list.get(j).x,
                    list.get(i).y, list.get(j).y
                );

                if (i == left && j == i + 1) {
                    minDistance = distance;
                } else if (distance < minDistance) {
                    minDistance = distance;
                } else {
                    // if current distance is greater than
                    // minDistance, break the loop because
                    // points are sorted w.r.t Y coordinate
                    // so subsequent points will be at greater distance
                    // than current point at index i
                    break;
                }
            }
        }

        return minDistance;
    }

    static double computeHybridMin(List<Point> filteredList) {
        return computeMinDistance(filteredList, 0, filteredList.size());
    }

    static double calculateDistance(double x1, double x2, double y1, double y2) {
        double xDiff = x1 - x2;
        double yDiff = y1 - y2;
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    static double getMinDistance(List<Point> list, int left, int right) {
        // initially when full list is passed to this function,
        // left and right will be -1
        // this is done to remove duplicate function that took
        // left and right index parameters
        if (left == -1 && right == -1) {
            left = 0;
            right = list.size();
        }

        if (right - left <= 3) {
            return computeMinDistance(list, left, right);
        }

        int mid = (left + right) / 2;

        double distanceLeft = getMinDistance(list, left, mid);
        double distanceRight = getMinDistance(list, mid, right);

        double currentMin = Math.min(distanceLeft, distanceRight);
        double midLine = (list.get(mid - 1).x + list.get(mid).x) / (double) 2;

        List<Point> filteredList = getFilteredList(list, left, right, midLine, currentMin);

        if (filteredList.size() > 0) {
            // sort points w.r.t Y coordinate
            Collections.sort(filteredList);
            currentMin = Math.min(currentMin, computeHybridMin(filteredList));
        }

        return currentMin;
    }

    static double minimalDistance(int[] x, int[] y) {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < x.length; i++) {
            points.add(new Point(x[i], y[i]));
        }

        // sort points w.r.t X coordinate
        points.sort((p1, p2) -> {
            if (p1.x > p2.x) {
                return 1;
            } else if (p1.x < p2.x) {
                return -1;
            }

            return 0;
        });

        double distance = getMinDistance(points, -1, -1);

        // round up to 4 decimal places
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.FLOOR);

        return Double.parseDouble(df.format(distance));
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }

        System.out.println(minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
