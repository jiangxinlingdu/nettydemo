package io.netty.example.echo.test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalHashTest {

    public static void main(String[] args) {

        int[] array = {16,16<<1,16<<2,16<<3,16<<4,16<<6,16<<7,16<<8};

        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            Set<Integer> set = new HashSet<>(num);
            System.out.println(num);
            int threshold = setThreshold(num);
            for (int j = 0; j < threshold; j++) {
                int data = nextHashCode() & (num - 1);
                set.add(data);
            }
            System.out.println(set.size()==threshold);
        }

    }

    private static int setThreshold(int len) {
        return len * 2 / 3;
    }


    private static AtomicInteger nextHashCode =
        new AtomicInteger();

    private static final int HASH_INCREMENT = 0x61c88647;

    /**
     * Returns the next hash code.
     */
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }
}
