package com.chason.class01;

/**
 *  在有序数组中 找 >= 某个数最左侧的位置
 *  使用二分法
 *
 *  1，2，2，2，3，3，3，3，3，4，4，4    >= 2 最左侧的位置 1
 *
 */
public class Code05_BSNearestLeft {

    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 2, 2, 3, 3, 3, 3 ,4, 4, 4, 5};
        System.out.println(getNearestLeft(arr, 3));

    }

    public static int getNearestLeft (int[] arr, int target) {

        if (arr == null || arr.length == 0) {
            return -1;
        }

        int L = 0;
        int R = arr.length - 1;

        if (target < arr[L] || target > arr[R]) {  // target 不在数组区间中
            return -1;
        }

        int index = -1;     // 记录最左侧的对号
        int mid = 0;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] >= target) {     // left effective
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }


}
