package io.netty.example.echo.test;


import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

public class FastThreadLocalTest {
    private static FastThreadLocal<Integer> fastThreadLocal = new FastThreadLocal<>();

    public static void main(String[] args) {

        //if (thread instanceof FastThreadLocalThread) 使用FastThreadLocalThread更优，普通线程也可以
        new FastThreadLocalThread(() -> {
            for (int i = 0; i < 100; i++) {
                fastThreadLocal.set(i);
                System.out.println(Thread.currentThread().getName() + "====" + fastThreadLocal.get());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "fastThreadLocal1").start();


        new FastThreadLocalThread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "====" + fastThreadLocal.get());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "fastThreadLocal2").start();
    }
}