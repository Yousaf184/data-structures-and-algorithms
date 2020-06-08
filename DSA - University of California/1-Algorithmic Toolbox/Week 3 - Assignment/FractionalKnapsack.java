import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        int maxValuePerWeightIndex;
        int index = 0;
        int tempValue, tempWeight;
        int useWeight;
        double useValue;

        while (capacity > 0 && index < values.length) {
            maxValuePerWeightIndex = FractionalKnapsack.maxValuePerWeightIndex(values, weights, index);

            // if weight at 'maxValuePerWeightIndex' is less than capacity
            if (weights[maxValuePerWeightIndex] <= capacity) {
                value += values[maxValuePerWeightIndex];
                capacity -= weights[maxValuePerWeightIndex];

                /*
                 swap the value and weight at 'maxValuePerWeightIndex' with
                 value and weight at the 'index' position in values and weights array
                 so that in next iteration, index of max value per weight is searched from
                 the remaining values and weights
                */
                // save value and weight at index equal to 'index' in temp variables
                tempValue = values[index];
                tempWeight = weights[index];
                // put value and weight at 'maxValuePerWeightIndex' at the 'index' position
                values[index] = values[maxValuePerWeightIndex];
                weights[index] = weights[maxValuePerWeightIndex];
                // save value and weight in temp variables at position 'maxValuePerWeightIndex'
                values[maxValuePerWeightIndex] = tempValue;
                weights[maxValuePerWeightIndex] = tempWeight;
            }
            else { // if weight at 'maxValuePerWeightIndex' is less than capacity
                // calculate the amount of weight and value that can fit in the current capacity
                useWeight = weights[maxValuePerWeightIndex] - (weights[maxValuePerWeightIndex] - capacity);
                useValue = ((double) values[maxValuePerWeightIndex] / weights[maxValuePerWeightIndex]) * useWeight;

                // update capacity
                capacity -= useWeight;
                // update value
                value += useValue;
                // update weight and value at position 'maxValuePerWeightIndex' in
                // weights and values arrays respectively
                weights[maxValuePerWeightIndex] -= useWeight;
                values[maxValuePerWeightIndex] -= useValue;
            }

            index++;
        }

        // round up to 4 decimal places
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.FLOOR);

        return Double.parseDouble(df.format(value));
    }

    private static int maxValuePerWeightIndex(int[] values, int[] weights, int startIndex) {
        double maxValuePerWeight = 0;
        double currentMaxValuePerWeight;
        int maxIndex = 0;

        for(int i = startIndex; i < values.length; i++) {
            currentMaxValuePerWeight = (double) values[i] / weights[i];

            if (currentMaxValuePerWeight > maxValuePerWeight) {
                maxIndex = i;
                maxValuePerWeight = currentMaxValuePerWeight;
            }
        }

        return maxIndex;
    }

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       int n = scanner.nextInt();
       int capacity = scanner.nextInt();
       int[] values = new int[n];
       int[] weights = new int[n];
       for (int i = 0; i < n; i++) {
           values[i] = scanner.nextInt();
           weights[i] = scanner.nextInt();
       }

       System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
