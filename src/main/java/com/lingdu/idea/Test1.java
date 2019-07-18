package com.lingdu.idea;

/**
 * @author 匠心零度
 */
public class Test1 {
    public static void main(String[] args) {
        //转换为lambda形式
        new Thread(new Runnable() {
            @Override public void run() {
                System.out.println("test");
            }
        }).start();
    }
}
