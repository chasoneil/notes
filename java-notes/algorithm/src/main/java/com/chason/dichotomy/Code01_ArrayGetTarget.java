package com.chason.dichotomy;

/**
 * 使用二分法 从一个有序数组中 查找某个数是否存在
 */
public class Code01_ArrayGetTarget {

    public static boolean isExist(int[] sortedArr, int target) {

        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }

        int L = 0;
        int R = sortedArr.length-1;

        if (target < sortedArr[L] || target > sortedArr[R]) {
            return false;
        }

        int mid = 0;
        while (L < R) {
            mid = L + (R - L) >> 1;
            if (sortedArr[mid] == target) {
                return true;
            } else if (sortedArr[mid] > target) { // left effective
                R = mid - 1;
            } else {  // right effective
                L = mid + 1;
            }
        }

        return sortedArr[L] == target;




    }

}
