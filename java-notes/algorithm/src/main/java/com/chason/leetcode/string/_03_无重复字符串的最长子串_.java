package com.chason.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class _03_无重复字符串的最长子串_ {

    public static void main(String[] args) {
        String s = "pwwskw";
        System.out.println(lengthOfLongestSubstring1(s));

    }


    /*
        使用hashmap  出现重复则 key 对应的字符, value 无所谓
        使用map有个注意的地方，
        当出现重复的字符的时候，这个重复字符前面的字符都不能再存放在Map中了

        todo 为解决
     */
    public static int lengthOfLongestSubstring(String s) {

        if (s == null || s.equals("")) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();

        int max = 0;
        int repeat = 0;
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {  // 出现了重复

                max = Math.max(max, repeat);
                // 重新判断我的repeat什么数开始计数
                repeat = i - map.get(ch);
                map.put(ch, i);
            } else {
                repeat++;
                map.put(ch, i);
            }
        }

        return Math.max(max, repeat);
    }

    /*
        滑动窗口
        双指针
     */
    public static int lengthOfLongestSubstring1(String s) {

        int p1 = 0;
        int p2 = 0;

        int max = 0;

        int repeat = 0;

        Map<Character, Integer> map = new HashMap<>();

        while (p1 < s.length()) {

            char ch = s.charAt(p1);

            if (map.containsKey(ch)) {   // 出现了重复
                repeat = p1 - p2;
                max = Math.max(max, repeat);

                if (p2 < map.get(ch) + 1) {
                    p2 = map.get(ch) + 1;
                }

                map.put(ch, p1);
            } else {
                map.put(ch, p1);
            }
            p1++;
        }

        repeat = p1 - p2;

        return Math.max(max, repeat);

    }


}
