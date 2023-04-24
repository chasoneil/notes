package com.chason.sort.heap;

/**
 * 根据数组生产大根堆
 */
public class Code01_ArrayToHeap01 {


    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 4, 6, 8, 0};
        Code01_ArrayToHeap01 heap01 = new Code01_ArrayToHeap01();
        for (int i=0; i<arr.length; i++) {
            heap01.push(arr[i]);
        }
        System.out.println(heap01.heap);
    }

    private int[] heap; //堆数组

    private int size;

    private static final int capacity = 10;

    public Code01_ArrayToHeap01 () {
        heap = new int[capacity];
    }

    public void push (int value) {

        if (size == capacity) {
            throw new RuntimeException("heap is full");
        }

        heap[size] = value;
        heapInsert(heap, size++);
    }

    /*
        当数组中为空的时候 第一个肯定是大根堆
        第二个如果比第一个小，那么也是个大根堆
        当出现比第一个大的时候，就不是大根堆了，需要将父节点和子节点做交换，保证大根堆
        每次比对都是跟自己的父节点做比较
     */
    private void heapInsert(int[] arr, int index) {

        while (arr[index] > arr[(index -1) / 2]) {
            swap(arr, index, (index-1) / 2);
            index = (index - 1) / 2;
        }

    }

    public static void swap (int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 弹出最大值, 同时剩下的数还要是一个大根堆
     * @return
     */
    public int pop() {

        if (size == 0) {
            return -1;
        }

        if (size == 1) {
            size--;
            return heap[0];
        }

        int res = heap[0];      // 大根堆的最大数一定是在头节点
        heapIfy(0);     // 从数组的头开始
        return res;
    }

    private void heapIfy (int index) {

        swap(heap, 0, --size);   // 把最后一个数给到最前面

        // 停止的条件是 没有孩子节点了 因为你是往下看

        int left = 2 * index + 1;   // 左孩子
        while (left < size) {       // 还没有越界， 存在左孩子 ，至于有没有右孩子，咱们再看，但是如果没有左孩子一定没有右孩子

            // 左右俩孩子中谁大，谁把下标给largest
            int largest = left + 1 < size && heap[left] > heap [left+1] ? left : left + 1;

            largest = heap[index] > heap[largest] ? index : largest;    // 将较大的index 给largest

            if (largest == index) {     // 说明子孩子没有一个能比父节点大
                break;
            }

            swap(heap, index, largest);
            index = largest;
            left = index * 2 + 1;

        }

    }

}
