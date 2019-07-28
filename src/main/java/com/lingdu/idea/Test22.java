package com.lingdu.idea;

/**
 * @author 匠心零度
 */
public class Test22 {

    public static boolean a = true;
    public static void main(String[] args) {
        String str = test();
        System.out.println(str);
    }

    private static String test() {
        for (int i = 0; i < 100; i++) {
            System.out.println("abc");
            for (int i1 = 0; i1 < 20; i1++) {
                System.out.println("cdb");
            }
            for (int i1 = 0; i1 < 20; i1++) {
                System.out.println("cdb");
            }
        }
        return "2";
    }

    private static String test2() {
        for (int i = 0; i < 100; i++) {
            System.out.println("abc");
            for (int j1 = 0; j1 < 20; j1++) {
                System.out.println("cdb");
            }
            for (int j3 = 0; j3 < 20; j3++) {
                System.out.println("cdb");
            }
        }
        return "2";
    }


}
