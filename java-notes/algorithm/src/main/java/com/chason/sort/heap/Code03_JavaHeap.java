package com.chason.sort.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Java 中自己实现的堆结构
 */
public class Code03_JavaHeap {


    public static void main(String[] args) {

        PriorityQueue<Integer> queue = new PriorityQueue<>(10);
        addElements(queue);
        test01(queue);
        System.out.println("\n===========");

        queue = new PriorityQueue<>(10, new MyComp());
        test02(queue);
        System.out.println("\n===========");

    }

    private static void addElements (PriorityQueue queue) {
        queue.add(3);
        queue.add(5);
        queue.add(2);
        queue.add(6);
        queue.add(4);
        queue.add(8);
    }


    public static void test01(PriorityQueue<Integer> queue) {

        // 默认的实现是小根堆
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
    }

    // 通过比较器的方式，让小根堆变成大根堆
    public static void test02 (PriorityQueue<Integer> queue) {

        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }

    }

    static class MyComp implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

}
