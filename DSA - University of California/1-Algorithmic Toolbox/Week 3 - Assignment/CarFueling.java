import java.util.Scanner;

public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
        // if car can go more miles, with full tank, than the distance
        // to the destination, no refills needed
        if (tank >= dist) {
            return 0;
        }

        int minRefills = 0;
        int currentPosition = 0;
        int lastStop = 0;
        int tankLeft = tank;
        int index = 0;
        boolean refilled = false;

        while (currentPosition < dist) {
            if (currentPosition + tankLeft >= stops[index]) {
                currentPosition = stops[index];
            }

            // update tankLeft only if there's was no refill at current stop
            if (!refilled) {
                tankLeft -= (currentPosition - lastStop);
            }

            if (index >= stops.length - 1) {
                // if current position is equal to last stop
                // then refill only if destination can't be reached
                // with the current tank capacity but will be reachable after a refill
                if (currentPosition + tankLeft < dist && currentPosition + tank >= dist) {
                    minRefills++;
                }
                break;
            }

            if (stops[index + 1] - currentPosition <= tankLeft) {
                lastStop = currentPosition;
                index++;
                // reset refilled flag variable so that tankLeft can be updated correctly
                refilled = false;
            } else if (stops[index + 1] - currentPosition > tank) {
                // can't go to next stop even with refill
                return -1;
            } else {
                // next stop is too far but reachable with a refill
                // refill the car
                tankLeft = tank;
                minRefills++;
                refilled = true;
            }
        }

        // not possible to reach the destination
        if (currentPosition + tank < dist) {
            return -1;
        }

        return minRefills;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int[] stops = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }
        
        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
