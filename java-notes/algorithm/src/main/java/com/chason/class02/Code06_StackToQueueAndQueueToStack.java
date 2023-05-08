package com.chason.class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 使用栈实现队列
 * 使用队列实现栈
 * 底层只能用栈
 *
 */
public class Code06_StackToQueueAndQueueToStack {


    /**
     * 栈实现队列
     * 逻辑非常简单，使用两个栈，第一个栈Push栈，第二个栈Pop栈
     * 我们知道 从 Push 往 Pop导入数据，可以将数据的顺序颠倒过来，但是需要满足两个原则
     * 1. 只有Pop栈中没有数据的时候，可以从Push栈中导入数据过来
     * 2. 如果发生导数据的行为，那么一定要是一次性把Push栈中的数据导完，不能留数据
     * 这样，以后返回可以直接从Pop栈中返回了
     */
    static class MyQueueByStack {

        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public MyQueueByStack () {
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        public void offer(int value) {
            pushToPop();
            pushStack.push(value);
        }

        public int poll() {
            pushToPop();
            return popStack.pop();
        }

        /**
         * 记住上面满足的两个条件
         */
        private void pushToPop() {
            if (popStack != null) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }
    }


    /**
     * 使用两个队列
     *
     * data 存放数据 ； help 辅助
     * push 的时候往data中放数据
     *
     * poll 的时候，首先把 data 中的数据往 help 中导入，当data中只剩最后一个数据的时候，返回给用户
     * data 变成 help  help变成data
     *
     */
    static class MyStackByQueue {

        Queue<Integer> data = new LinkedList<>();
        Queue<Integer> help = new LinkedList<>();

        public void push (int value) {
            data.add(value);
        }

        public Integer pop () {

            if (data.size() == 0) {
                throw new RuntimeException("Stack is empty.");
            }

            for (int i=0; i<data.size() - 1; i++) {
                help.add(data.poll());
            }

            int result = data.poll();
            data = help;
            return result;
        }
    }


}
