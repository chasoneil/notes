package com.chason.class03;

/**
 * 求数组小和
 * 在一个数组中，一个数左边比他小的数的总和，叫数的小和，所有的数的小和加起来，叫做数组小和
 *
 * 例如 [1, 3, 4, 2, 5]
 * 1 左边没有比他小的
 * 3 左边比他小的 1
 * 4 左边比他小的 1， 3
 * 2 左边比他小的 1
 * 5 左边比他小的 1 3 4 2
 * 所以数组小和 = 1 + 1 + 3 + 1 + 1 + 3 + 4 + 2 = 16
 *
 *
 * 题解：
 * 使用归并的思想， 在merge的时候，如果左组的数小于右组的数，产生小和
 * 如果左组 = 右组  先拷贝右组的数 不产生小和
 * 如果左组 > 右组 先拷贝左组的数，不产生小和
 */
public class Code02_SmallSum {


    public static void smallSum (int[] arr) {
        process(arr, 0, arr.length - 1);
    }

    // 在arr 数组的L ... R位置排序并产生小和返回
    public static int process (int[] arr, int L, int R) {

        if (L == R) {
            return 0;
        }

        int M = L + ((R - L) >> 1);

        // 所有的小和 = 左边排序时候产生的小和 + 右边排序时候产生的小和 + merge时候产生的小和
        return process(arr, L, M) +
               process(arr, M + 1, R) +
               merge(arr, L, M, R);
    }


    public static int merge (int[] arr, int L, int M, int R) {

        int[] help = new int[R - L + 1];

        int p1 = L;
        int p2 = M+1;

        int smallSum = 0; // merge 过程中产生的小和

        int i=0;
        while (p1 <= M && p2 <= R) {

            // 当 左组 < 右组的时候 我们要累加， 出现了多少个左组值的小和
            smallSum += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M) {
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (i=0; i<help.length; i++) {
            arr[L + i] = help[i];  // help 从0开始但是 arr从L开始
        }

        return smallSum;
    }

}
