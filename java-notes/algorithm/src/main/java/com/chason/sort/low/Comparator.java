package com.chason.sort.low;

import com.chason.review._HeapSort_review_;

import java.util.Arrays;

/**
 * 数组排序对数器
 */
public class Comparator {

    public static int[] generateRandomArray (int maxSize, int maxValue) {
        int[] arr = new int[(int)(Math.random() * (maxSize +1))];
        for (int i=0; i<arr.length; i++) {
            // 数组是正数到负数的随机
            arr[i] = (int) ((maxValue+1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void start() {
        int testTime = 50000;
        int maxValue = 100;
        int maxSize  = 100;

        boolean succeed = true;

        for (int i=0; i<testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

            // use your method to sort arr1
            _HeapSort_review_.heapSort(arr1);
            Arrays.sort(arr2);

            if (!isEqual(arr1, arr2)) {
                succeed = false;
            }
        }

        if (!succeed) {
            System.out.println("Your method is wrong!");
        } else {
            System.out.println("Your method is right");
        }
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {

        if (arr1 == null && arr2 != null) {
            return false;
        }

        if (arr1 != null && arr2 == null) {
            return false;
        }

        if (arr1 == null && arr2 == null) {
            return true;
        }

        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i=0; i<arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

    public static int[] copyArray(int[] source) {
        int[] target = new int[source.length];

        for (int i=0; i<source.length; i++) {
            target[i] = source[i];
        }
        return target;
    }


    public static void main(String[] args) {
        start();
    }


    private static void printArray(int[] arr) {

        if (arr == null || arr.length == 0) {
            System.out.println("array is null");

            return;
        }

        StringBuilder sb = new StringBuilder();

        for (int i=0; i<arr.length; i++) {
            sb.append(arr[i] +  " ");
        }
        System.out.println(sb.toString());
    }

}
