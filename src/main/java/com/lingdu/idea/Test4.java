package com.lingdu.idea;

import java.text.SimpleDateFormat;

/**
 * @author 匠心零度
 */
public class Test4 {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        String dateTime = "2019-12-31 23:59:59";
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10; i1++) {
                    //捕获异常
                    //System.out.println(simpleDateFormat.parse(dateTime));
                }
            }).start();
        }
    }
}
