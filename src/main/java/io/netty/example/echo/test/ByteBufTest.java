package io.netty.example.echo.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;

public class ByteBufTest {
    public static void main(String[] args) {
        ByteBufAllocator  alloc = PooledByteBufAllocator.DEFAULT;
        ByteBuf byteBuf = alloc.directBuffer(8*1024);
    }
}