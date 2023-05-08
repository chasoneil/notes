package com.chason.class02;

import java.util.Stack;

/**
 * 实现一个栈, 要求除了最常用的 push pop 之外，还提供一个方法  getMin() 可以获取当前栈中的最小值
 * 要求: 时间复杂度要求 O(1)
 * 注意: 因为栈可以一致push pop数据，所以栈中的最小值可能也在不断变化  getMin()的返回值也在不断变化
 */
public class Code05_StackMin {

    /**
     *
     * 思路: 栈动态最小值的最好方式是使用双栈， 数据栈存放真实数据
     * min栈，存放当前栈空间的最小值
     *
     * 当两个栈都为null  存入第一个数字 3 数据栈和最小栈都存入3
     * 当栈不为空的时候， 存入4的时候，数据栈存入真实数据，最小栈会将新数据和最小栈的栈顶比较，存最小值 所以最小栈存 3
     * 以此类推，最小栈和数据栈长度一致，栈顶是对应数据栈长度的最小值
     *
     */

    static class MyStack {

        private Stack<Integer> data;
        private Stack<Integer> min;

        public MyStack() {
            data = new Stack<>();
            min = new Stack<>();
        }

        public void push (int value) {
            if (data.isEmpty()) {
                data.push(value);
                min.push(value);
            } else {
                data.push(value);
                int minValue = min.peek();
                int pushValue = minValue > value ? min.push(value) : min.push(minValue);
            }
        }

        public int pop() {
            if (data.empty()) {
                throw new RuntimeException("Stack is empty.");
            }

            min.pop();
            return data.pop();
        }

        public int getMin() {
            if (min.empty()) {
                throw new RuntimeException("Stack is empty.");
            }
            return min.peek();
        }

    }
}
