package com.chason.class04;

import java.util.PriorityQueue;

/**
 * 将一个几乎有序的数组进行排序，选择最合适的排序方式
 *
 * 几乎有序： 数组中每个元素在排序之后移动的距离都小于某个值K ,且K相对于数组长度比较小
 */
public class Code02_SortedSort {

    public static void main(String[] args) {

        int[] arr = new int[] {3, 4, 1, 2, 6, 5, 8, 7};
        sortedSort(arr, 3);
        System.out.println(arr);
    }



    public static void sortedSort(int[] arr, int k) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // 先将 0 - K的数组成小根堆
        for (int i=0; i<k; i++) {
            heap.add(arr[i]);
        }

        int[] help = new int[arr.length];
        int index = 0;
        while (!heap.isEmpty()) {
            help[index] = heap.poll();
            if (k + index < arr.length) {
                heap.add(arr[k + index]);
            }
            index++;
        }

        for (int i=0; i<arr.length; i++) {
            arr[i] = help[i];
        }
    }


    // 系统自带的堆
    public static void systemHeap() {

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        heap.add(5);
        heap.add(3);
        heap.add(2);
        heap.add(6);
        heap.add(7);
        heap.add(1);
        heap.add(8);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

    }


}
