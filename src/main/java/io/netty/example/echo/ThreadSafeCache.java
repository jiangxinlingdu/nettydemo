package io.netty.example.echo;

public class ThreadSafeCache {
    int result;

    public int getResult() {
        return result;
    }

    public synchronized void setResult(int result) {
        this.result = result;
    }

    public static void main(String[] args) {
        ThreadSafeCache threadSafeCache = new ThreadSafeCache();

        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                int x = 0;
                while (threadSafeCache.getResult() < 100) {
                    x++;
                }
                System.out.println(x);
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadSafeCache.setResult(200);
    }
}
