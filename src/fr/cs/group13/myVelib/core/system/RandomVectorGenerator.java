package fr.cs.group13.myVelib.core.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class for generating a random vector of integers.
 */
public class RandomVectorGenerator {
    /**
     * Generates a random vector of integers with a specified length, maximum value, and total sum.
     *
     * @param n         the number of integers in the vector
     * @param maxValue  the maximum value for each integer
     * @param totalSum  the desired sum of the numbers
     * @return a list containing the generated random vector
     */
    public static List<Integer> generateVector(int n, int maxValue, int totalSum) {
        List<Integer> vector = new ArrayList<>();
        int sumToDistribute = totalSum;
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int bound = Math.min(maxValue,sumToDistribute);
            int num = random.nextInt(bound+1);
            vector.add(num);
            sumToDistribute -= num;
        }
        return vector;
    }

    /**
     * Generates random GPS coordinates within the specified range.
     *
     * @param maxX the maximum value for the x-coordinate
     * @param maxY the maximum value for the y-coordinate
     * @return an array containing the randomly generated GPS coordinates [x, y]
     */
    public static double[] getRandomCord(double maxX, double maxY){
        Random random = new Random();
        double x = random.nextDouble()*maxX;
        double y = random.nextDouble()*maxY;
        return new double[]{x, y};
    }
    public static void main(String[] args) {
        int n = 5;  // Number of integers in the vector
        int min = 10;  // Maximum value for each integer
        int totalSum = 40;  // Desired sum of the numbers

        List<Integer> randomVector = generateVector(n, min, totalSum);
        System.out.println(randomVector);
        int sum = randomVector.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);  // Verify that the sum is equal to totalSum
    }
}
