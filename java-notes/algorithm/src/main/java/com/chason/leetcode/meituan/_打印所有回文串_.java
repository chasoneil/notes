package com.chason.leetcode.meituan;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文串就是正着读和反着读一样的字符串
 * 比如 abcba
 */
public class _打印所有回文串_ {

    public static List<String> simpleRes = new ArrayList<>();

    public static List<List<String>> allRes = new ArrayList<>();

    public static String[][] partition(String s) {
        return null;

    }

    public static List<String> process (String str, int start) {
        return null;
    }


    public static boolean isPalindrome (String str, int start, int end) {
        if (start > end) {
            return false;
        }

        while (start <= end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
