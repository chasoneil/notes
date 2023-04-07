package com.chason.sort.low;

/**
 * 插入排序
 *
 * 当有一个数组 [6, 3, 4, 5, 1, 2]
 * 我们期望0-0 位置有序 √  天然就做到了
 * 我们期望0-1 上有序 ×  怎么做
 *     让 1位置的数(3) 往前看， 如果前一个数比自己大，那么就和他交换，然后继续看
 *     直到 前一个数比自己小或者前面已经没有数的时候停下
 *
 * 我们期望0-2有序 ×
 *     让2位置的数 (4) 往前看，如果前一个比自己大，交换，继续往前看
 *     直到前一个数比自己小或者前面没有数
 *
 * 依次往下
 *
 *
 */
public class Code03_InsertSort {

    public static void main(String[] args) {

        int[] arr = {4, 6, 3, 1, 7};
        insertSort(arr);
        System.out.println(arr);

    }


    public static void insertSort(int[] arr) {

        if (arr == null || arr.length == 1) {
            return;
        }

        // 0-0 默认有序
        // 从 0-1开始
        for (int p1=1; p1<arr.length; p1++) {

            // 当 已经到了数组头或者 前一个数比自己小的时候 停下
            for (int p2 = p1; (p2 > 0 && arr[p2-1] > arr[p2]); p2--) {

                if (arr[p2] < arr[p2-1]) {
                    swap(arr, p2, p2-1);
                }
            }
        }
    }

    public static void swap(int[] arr, int p1, int p2) {
        arr[p1] = arr[p1] ^ arr[p2];
        arr[p2] = arr[p1] ^ arr[p2];
        arr[p1] = arr[p1] ^ arr[p2];
    }

}
