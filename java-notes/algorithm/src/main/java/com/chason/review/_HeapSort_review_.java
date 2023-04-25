package com.chason.review;

public class _HeapSort_review_ {

    public static void heapSort(int[] arr) {

        if (arr == null || arr.length < 2) return;

        int[] heap = new int[arr.length];

        for (int i=0; i<arr.length; i++) {
            heap[i] = arr[i];
            heapInsert(heap, i);
        }

        int size = heap.length;
        swap(heap, 0, size-1);
        size--;

        while (size > 0) {
            heapIfy(heap, 0, size);
            swap(heap, 0, size-1);
            size--;
        }

        for (int i=0; i<arr.length; i++) {
            arr[i] = heap[i];
        }
    }

    // 下沉
    public static void heapIfy (int[] heap, int index, int size) {

        int left = index * 2 + 1;

        while (left < size) {
            int largest = left;
            if (left + 1 < size && heap[left] < heap[left + 1])
                largest = left + 1;

            if (heap[largest] < heap[index]) {
                largest = index;
            }

            if (largest == index) break;

            swap(heap, index, largest);
            index = largest;
            left = index * 2 + 1;
        }

    }

    public static void heapInsert(int[] heap, int index) {

        if (index == 0) return;

        while (heap[index] > heap[(index-1) / 2]) {
            swap(heap, index, (index-1) / 2);
            index = (index -1) / 2;
        }

    }

    public static void swap (int[] arr, int i, int j) {
        if (i == j) return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


}
