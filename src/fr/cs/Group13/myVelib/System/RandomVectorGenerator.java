package fr.cs.Group13.myVelib.System;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomVectorGenerator {
    public static List<Integer> generateVector(int n, int maxValue, int totalSum) {
        List<Integer> vector = new ArrayList<>(n);
        int remaining = totalSum;
        Random random = new Random();
        int count = 0;
        for (int i = 0; i < n; i++) {
            vector.add(0);
        }
        while (remaining>0 || count<n) {
            int index = count%n;
            int bound = Math.min(maxValue-vector.get(index),remaining);
            int num = random.nextInt(bound+1);
            vector.set(index, vector.get(index)+num);
            count++;
            remaining -= num;
        }
        System.out.println(remaining);
        return vector;
    }

    public static void main(String[] args) {
        int n = 5;  // Number of integers in the vector
        int maxValue = 10;  // Maximum value for each integer
        int totalSum = 40;  // Desired sum of the numbers

        List<Integer> randomVector = generateVector(n, maxValue, totalSum);
        System.out.println(randomVector);
        int sum = randomVector.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);  // Verify that the sum is equal to totalSum
    }
}
