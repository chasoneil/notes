package com.chason.sort.low;

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


        }


    }

    public static int[] copyArray(int[] source) {
        int[] target = new int[source.length];

        for (int i=0; i<source.length; i++) {
            target[i] = source[i];
        }
        return target;
    }

}
