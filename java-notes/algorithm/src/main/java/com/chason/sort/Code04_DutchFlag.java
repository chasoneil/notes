package com.chason.sort;

/**
 * 快速排序
 * partition 2
 * 荷兰国旗问题
 */
public class Code04_DutchFlag {

    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 5, 1, 3};
        //dutchFlag(arr, 3);
        fastSortPartition(arr, 0, arr.length - 1);

        System.out.println(arr);

    }

    public static void dutchFlag (int[] arr, int target) {

        if (arr == null || arr.length == 0) return;

        // 0 - arr.length-1
        int L = -1;
        int R = arr.length;

        int i=0;
        while (i < R) {     // 注意这个结束的位置

            if (arr[i] < target) {
                swap(arr, L+1, i);
                L++;
                i++;
            } else if (arr[i] == target) {
                i++;
            } else {
                swap(arr, i, R-1);
                R--;
            }
        }
    }

    /**
     *  还是荷兰国旗过问，但是这次需求发生变化
     *  给定任意一个数组，还是荷兰国旗的思路，我们target 默认选中 arr[R]位置的数
     *  要求对数组的L-R位置做partition
     *  返回target 的左边界和右边界
     */
    public static int[] fastSortPartition(int[] arr, int L, int R) {

        if (arr == null || arr.length == 0) {
            return new int[] {-1, -1};
        }

        int less = L-1;
        int more = R+1;

        int index = L;
        int target = arr[R];
        while (index < more) {

            if (arr[index] == target) {
                index++;
            } else if (arr[index] < target) {
                swap(arr, index, less+1);
                less++;
                index++;
            } else {
                swap(arr, index, more-1);
                more--;
            }
        }

        return new int[] {less+1, more-1};
    }


    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
