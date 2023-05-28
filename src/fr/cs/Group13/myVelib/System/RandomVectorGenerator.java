package fr.cs.Group13.myVelib.System;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomVectorGenerator {
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
        System.out.println(sumToDistribute);
        return vector;
    }
    public void distributeSum(List<Integer> vector, int sumToDistribute, int maxValue){
        int i = 0;
        int length = vector.size();
        Random random = new Random();
        while (sumToDistribute>0){
            if vector.get(i)<
        }

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
