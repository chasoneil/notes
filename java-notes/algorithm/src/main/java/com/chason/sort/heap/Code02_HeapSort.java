package com.chason.sort.heap;

public class Code02_HeapSort {

    public static void sort01(int[] arr) {

        // 1. 先依次从数组中取出一个数，然后把 0 - N-1范围都变成大根堆
        // 2. 把最前面一个数和最后一位交换 (大根堆的根节点一定是最大的，那么他就在排序号的数组的最后面)
        // 3. 把剩下的 0 - N-2 再变成一个大根堆 继续上面的操作

        for (int i=0; i<arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);

        while (heapSize > 0) {
            heapIfy(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }


    private static void heapInsert(int[] arr, int index) {

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

    private static void heapIfy (int[] arr, int index, int heapSize) {


        swap(arr, index, --heapSize);   // 把最后一个数给到最前面

        // 停止的条件是 没有孩子节点了 因为你是往下看
        int left = 2 * index + 1;   // 左孩子
        while (left < heapSize) {       // 还没有越界， 存在左孩子 ，至于有没有右孩子，咱们再看，但是如果没有左孩子一定没有右孩子

            // 左右俩孩子中谁大，谁把下标给largest
            int largest = left + 1 < heapSize && arr[left] > arr [left+1] ? left : left + 1;

            largest = arr[index] > arr[largest] ? index : largest;    // 将较大的index 给largest

            if (largest == index) {     // 说明子孩子没有一个能比父节点大
                break;
            }

            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;

        }

    }



}
