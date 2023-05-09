package com.chason.class03;

/**
 * 快速排序
 * Partition Q1:
 *
 * 1.0 版本的题解
 * 创建一个左区域  数组依次遍历
 * 当 arr[i] <= target 将i位置的数和左边界右侧的第一个数交换， 小于区域右阔
 * 否则， i++
 *
 * 2.0 版本题解
 * 有一个左边界  和一个 右边界  数组依次遍历
 * 当前 arr[i] == num  直接 i++
 * arr[i] < num  将当前位置的数和小于区域的右一个数交换 小于区域右阔  i++
 * arr[i] > num  将当前位置的数和大于区域的左一个交换 大于区域左扩  i停在原地
 *
 */
public class Code03_FastSort {

    // 快排 1.0 版本
    public static void fastSort1(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        process1(arr, 0, arr.length - 1);
    }

    // 快排2.0版本
    public static void fastSort2 (int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        process2(arr, 0, arr.length - 1);

    }

    // 快排3.0版本
    public static void fastSort3 (int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }



    }

    public static void process1 (int[] arr, int L, int R) {

        if (L >= R) {
            return;
        }

        // arr[R] 做划分  最终的结果肯定是 [ <= arr[R]  |  > arr[R] ]
        int M = partition1(arr, L , R);  // 是以arr[R] 为 target 最终产生的 target 位置
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }


    public static void process2 (int[] arr, int L, int R) {

        if (L >= R) {
            return;
        }

        int[] area = partition2(arr, L , R);
        process2(arr, L, area[0] - 1);
        process2(arr, area[1] + 1, R);
    }

    public static void process3 (int[] arr, int L, int R) {

        if (L >= R) {
            return;
        }

        swap(arr, (int)(Math.random() * (R-L+1)) + L ,R);   // 随机将数组中的一个数和数组最后的数做交换
        int[] area = partition2(arr, L , R);
        process2(arr, L, area[0] - 1);
        process2(arr, area[1] + 1, R);
    }


    /**
     * 1.0版本的题解
     * 返回 arr[L...R] 上 arr[R] 作为target 最终所在的位置 index
     * 将 arr 分成两个部分 <= arr[R] 的在左边  >arr[R] 的在右边
     * @param arr
     * @param L
     * @param R
     */
    public static int partition1(int[] arr, int L ,int R) {

        if (L < R) {
            return -1;
        }

        if (L == R) {
            return L;
        }

        int less = L - 1; // 左边界

        int index = L;

        while (index < R) { // 以arr[R] 作为划分的数 那么本身这个数index就没有意义了

            if (arr[index] < arr[R]) {
                swap(arr, index, less+1);
                less++;
            }
            index++;
        }

        swap(arr, less+1, R);
        return less+1;
    }


    // 在 arr[L...R] 上做荷兰国旗问题  target 我们使用arr[R] 位置的数
    // 返回 等于区域的左边界和右边界
    public static int[] partition2 (int[] arr, int L, int R) {

        if (L > R) {
            return new int[] {-1, -1};
        }

        if (L == R) {
            return new int[] {0, 0};
        }

        int less = -1; // < target的区域的边界
        int more = arr.length; // > target的区域边界

        int target = arr[R];

        int index = 0;
        while (index < more) {  // partition2.0
            if (arr[index] < target) {
                swap(arr, less+1, index);
                less++;
                index++;
            } else if (arr[index] == target) {
                index++;
            } else {
                swap(arr, index, more-1);
                more--;
            }
        }

        return new int[] {less+1, more-1};
    }

    private static void swap (int[] arr, int i, int j) {

        if (i == j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
