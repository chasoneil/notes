package com.chason.sort;

/**
 * 归并排序
 */
public class Code01_MergeSort {


    public static void main(String[] args) {

        int[] arr = {3, 1, 2, 4, 0, 6};
        mergeSort(arr);

        System.out.println(arr);

    }


    public static void mergeSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        sort(arr, 0, arr.length - 1);
    }


    /**
     * 递归的函数，对数组 L - R 范围进行排序
     * @param arr
     * @param L
     * @param R
     */
    public static void sort(int[] arr, int L, int R) {

        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        sort(arr, L, mid);
        sort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }


    /**
     * 对两个已经排好序的数组进行重新排序 [L - R]
     * @param arr 原数组
     * @param L   排序的最左侧指针位置
     * @param M   排序的中间指针位置
     * @param R   排序的最右侧指针位置
     */
    public static void merge(int[] arr, int L, int M, int R) {

        int[] tmpArr = new int[R - L + 1];  // 临时数组用于存放最新的排序的数组
        int i = 0; // tmpArr 使用的 index

        int p1 = L;     // L ～ M 中最左侧的指针
        int p2 = M + 1;   // M+1 ～ R中最左侧的指针

        while (p1 <= M && p2 <= R) {  // 左右指针都不越界
            tmpArr[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];   // 谁小就把谁放到临时的数组中，并把对应的指针自增 i也要自增
        }

        while (p1 <= M) { // p2 越界
            tmpArr[i++] = arr[p1++];
        }

        while (p2 <= R) { // p1 越界
            tmpArr[i++] = arr[p2++];
        }

        // 将临时数组中的数据重新拷贝到原来的数组中
        for (i=0; i < tmpArr.length; i++) {
            arr[L + i] = tmpArr[i];
        }
    }
}
