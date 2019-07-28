package com.lingdu.idea;

/**
 * @author 匠心零度
 */
public class Test19 {

    public static boolean a = true;
    public static void main(String[] args) {
        test();
        System.out.println(a);
    }

    //内联方法重构
    private static void test() {
        if (a) {
            return ;
        }
        doTest();
    }

    private static void doTest() {
    }
}
