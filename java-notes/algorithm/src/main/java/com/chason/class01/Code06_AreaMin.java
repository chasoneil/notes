package com.chason.class01;

/**
 * 局部最小问题
 * 1. 给定一个未排序的乱序数组 ， 任意相邻位置的值均不同， 返回任何一个局部最小的index
 *
 * 当 数组 index = 1 位置的数 > 数组 index = 0 位置的数的时候 ，我们称 index = 0 为局部最小 index左侧没有数了
 * 当 arr.length-1 位置的数 < arr.length -2 我们也叫局部最小， index 右侧没有值
 * 当 arr[i-1] > arr[i] <arr[i+1]  我们称 i 位置是局部最小
 *
 */
public class Code06_AreaMin {

    public static void main(String[] args) {

        int[] arr = {2 , 1, 0, -1, -2, 3, 2};
        System.out.println(areaMin(arr));

    }

    public static int areaMin (int[] arr) {

        if (arr == null || arr.length == 0) {
            return -1;
        }

        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }

        if (arr[arr.length-1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        // 二分法

        int left = 0;
        int right = arr.length - 1;


        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (arr[mid] > arr[mid - 1]) {  // 应该往左边二分 右侧区域的值无效
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {    // 相邻不等 所以不是大就是小  往右侧二分，左侧无效
                left = mid + 1;
            } else {        // mid 位置即比左边小又比右边小
                return mid;
            }
        }
        return left;
    }
}
