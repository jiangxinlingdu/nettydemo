package io.netty.example.echo.test;

import io.netty.channel.*;

import java.io.IOException;
import java.io.RandomAccessFile;


public class ChannelInboundHandlerAdapterFileRegion extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("FileRegion");


        new Thread(new ThreadTep(ctx.channel())).start();

        super.channelRead(ctx, msg);
    }



    class ThreadTep implements Runnable{
        private Channel channel;

        public ThreadTep() {
        }

        public ThreadTep(Channel channel) {
            this.channel = channel;
        }

        public void run() {


            try {
                RandomAccessFile raf = new RandomAccessFile("C:\\Windows\\System32\\drivers\\etc\\HOSTS", "r");
                ChannelFuture future = channel.writeAndFlush(new DefaultFileRegion(raf.getChannel(), 0,  raf.length()));
                future.addListener(new ChannelFutureListener() {
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("======isSuccess");
                        }
                    }
                });
            } catch (IOException e) {
            }
        }
    }
}