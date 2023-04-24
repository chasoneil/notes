package com.chason.review;

import com.chason.sort.Code05_FastSort;

public class _FastSort_review_ {

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 6, -1, 0, 8, 2};

        fastSort(arr);
        System.out.println(arr);
    }

    public static void fastSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length-1);
    }

    public static void process (int[] arr, int L, int R) {

        if (L >= R) {
            return;
        }

        int[] range = dutchFlag(arr, L, R);
        process(arr, L, range[0] - 1);
        process(arr, range[1] + 1 , R);
    }

    public static int[] dutchFlag (int[] arr, int L, int R) {

        if (L > R) {
            return new int[] {-1, -1};
        }

        if (L == R) {
            return new int[] {L, R};
        }

        int target = arr[R];

        int less = L-1;
        int more = R+1;
        int index = L;
        while (index < more) {

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

        return new int[] {less + 1, more - 1};
    }

    public static void swap (int[] arr, int i, int j) {
        if (i == j) return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
