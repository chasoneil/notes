package com.chason.leetcode.array;

public class _4_两个正序数组的中位数_ {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        double res = findMedianSortedArrays(nums1, nums2);
        System.out.println(res);
    }

    /*
        常规思路 先进行两个数组的合并 两个有序数组
        然后通过计算得中位数
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1 == null && nums2 == null) {
            return 0;
        }

        int newLength = 0;

        if (nums1 == null) {
            newLength = nums2.length;
        }

        if (nums2 == null) {
            newLength = nums1.length;
        }

        if (nums1 != null && nums2 != null) {
            newLength = nums1.length + nums2.length;
        }

        int[] arr = new int[newLength];

        int p1 = 0;
        int p2 = 0;

        int index = 0;

        while (p1 < nums1.length  && p2 < nums2.length) {
            if (nums1[p1] <= nums2[p2]) {
                arr[index] = nums1[p1];
                p1++;
            } else {
                arr[index] = nums2[p2];
                p2++;
            }
            index++;
        }

        while (p1 < nums1.length) {
            arr[index] = nums1[p1];
            p1++;
            index++;
        }

        while (p2 < nums2.length) {
            arr[index] = nums2[p2];
            p2++;
            index++;
        }

        System.out.println(arr);

        if (newLength % 2 == 1) {  // 奇数
            return (double) arr[newLength / 2];
        } else {
            return (double) (arr[newLength/2 - 1] + arr[newLength/2]) / 2;
        }

    }

}
