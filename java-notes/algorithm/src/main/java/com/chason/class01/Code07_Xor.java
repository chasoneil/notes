package com.chason.class01;

/**
 *
 * 异或的相关问题
 *
 */
public class Code07_Xor {


    // 数组中只有一个数出现了奇数次，其他的都是偶数次
    public static int printOddTimesNum1 (int[] arr) {
        int eor = 0;
        for (int i=0; i<arr.length; i++) {
            eor ^= arr[i];                  // 全异或
        }
        return eor;
    }

    // 数组中有两个数出现了奇数次
    public static int[] printOddTimesNum2 (int[] arr) {

        int eor = 0;

        for (int i=0; i<arr.length; i++) {
            eor ^= arr[i];
        }

        // eor = a ^ b
        // eor != 0
        // eor 必然在某个位上为1
        int rightOne = eor & (~ eor + 1);  // 找出最右侧那个1

        int leftOrRight = 0;  // eor'

        for (int i=0; i<arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                leftOrRight ^= arr[i];
            }
        }
        return new int[] {leftOrRight, eor ^ leftOrRight};
    }


}
