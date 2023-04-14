package com.chason.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class _1_两数之和_ {

    /*
       O(n^2)
       非常暴力的方法，很简单都能想出来
     */
    public int[] twoSum(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] res = new int[2];

        for (int i=0; i<nums.length; i++) {

            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    /*
      使用hash表，将查找某个特定元素从 O(N) -> O(1)
     */
    public int[] twoSum2(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>(); // key 数组对应位置的元素值  value 这个值所处的位置

        int[] res = new int[2];

        int other = 0;
        for (int i=0; i<nums.length; i++) {

            other = target - nums[i];
            /*
                 原来我们就要去数组里面找这个数了，但是现在我们去hash表里面找
             */
            if (map.containsKey(other)) { // 说明找到了，可以结束了
                res[0] = i;
                res[1] = map.get(other);
            } else {                        // 找不到 将这个数和位置记入hash表

                /*
                    为什么这么做? 因为当你刚开始，可能存在后面的匹配值，你hash为空匹配不到
                    但是随着你hash表往后移动，记录的值越来越多，后面的值可以找到前面的

                    这个地方有个巧妙的点，因为我们就找两数之和，所以如果出现相同的数 比如target = 6, 结果为 3， 3
                    那么数组中最多也就会出现两个3，不会出现三个，那么我hash表记录第一个位置之后，循环到第二个数的时候
                    从hash表中找到第一个就行了，不会覆盖掉
                 */
                map.put(nums[i], i);
            }
        }

        return res;

    }

}
