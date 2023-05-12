package com.chason.class04;

/**
 * 使用数组 手工创建一个 大根堆 的结构
 * 注意 ： heapInsert 和  heapify
 *
 * 2. 进行堆排序
 *
 */
public class Code01_Heap {


    private int[] heap;

    public int heapSize;

    public final int capacity;

    public Code01_Heap (int capacity) {
        heap = new int[capacity];
        this.capacity = capacity;
        heapSize = 0;
    }


    // 堆排序  堆排序的额外空间复杂度 O(1)
    public void heapSort(int[] arr) {

        if (arr == null || arr.length < 2) return;

        // 正常的方式
        // 将一个无序数组先变成大根堆 时间复杂度 O(N*logN)
//        for (int i=0; i<arr.length; i++) {
//            heapInsert(arr, i);
//        }

        // 优化的方式  时间复杂度 O(N)
        for (int i=arr.length -1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        int heapSize = arr.length;
        swap(arr, 0, heapSize);
        heapSize--;

        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, heapSize);
            heapSize--;
        }

    }


    public boolean isEmpty () {
        return heapSize == 0;
    }

    public boolean isFull () {
        return heapSize == capacity;
    }

    // 向堆中存放元素
    public void push (int value) {

        if (isFull()) {
            throw new RuntimeException("Heap is full.");
        }

        // 先把元素放到数组最后的位置然后再对元素的位置进行调整
        heap[heapSize] = value;
        heapInsert(heap, heapSize);
        heapSize++;
    }

    // 返回大根堆的头 （最大值）并弹出
    public int pop() {

        int result = heap[0];

        swap(heap, 0, heapSize-1);
        heapSize--;
        heapify(heap, 0, heapSize);
        return result;
    }

    // 从当前位置往上看，将当前位置的数调整成大根堆的形式
    private void heapInsert(int[] arr, int index) {

        while (arr[index] > arr[(index-1)/2]) {
            swap(arr, index, (index-1)/2);
            index = (index-1)/2;
        }

    }

    // 从头开始向下调整

    // 停止的条件: 要么我的左右两个节点都不比我大了， 要么我没有左右节点了
    private void heapify(int[] arr, int index, int size) {

        int left = index * 2 + 1;

        while (left < size) {   // 当我的左节点没有越界，证明我有左节点

            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;

            if (largest == index) {
                break;
            }

            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }


    }

    private static void swap (int[] arr, int i, int j) {
        if (i == j)
            return;

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }



}
