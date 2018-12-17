package io.netty.example.echo.test;


import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

public class FastThreadLocalTest {
    private static FastThreadLocal<Integer> fastThreadLocal = new FastThreadLocal<Integer>();

    public static void main(String[] args) {

        //if (thread instanceof FastThreadLocalThread) 所以 这里是FastThreadLocalThread而不是普通线程
        new FastThreadLocalThread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    fastThreadLocal.set(i);
                    System.out.println(Thread.currentThread().getName() + "====" + fastThreadLocal.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "fastThreadLocal1").start();


        new FastThreadLocalThread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "====" + fastThreadLocal.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "fastThreadLocal2").start();
    }
}