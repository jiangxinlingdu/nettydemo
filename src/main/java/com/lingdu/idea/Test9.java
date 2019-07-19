package com.lingdu.idea;

/**
 * @author 匠心零度
 */
public class Test9 {
    public static void main(String[] args) {

        boolean flag = false;
        int num;

        //if……else变成简单三目运算

        if (flag) {
            num = 6;
        } else {
            num = 10;
        }



        flag = test(flag);

        System.out.println(num);
    }

    private static boolean test(boolean flag) {
        //简化if……else

        if(flag){
            return true;
        }
        return false;
    }
}
