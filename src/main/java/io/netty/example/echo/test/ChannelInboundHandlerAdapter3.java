package io.netty.example.echo.test;

import io.netty.channel.*;


public class ChannelInboundHandlerAdapter3 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ChannelInboundHandlerAdapter3");


       /* //这段代码 怎么执行都是继续执行，不阻塞 最后，ractor线程里面都是同步的
        ChannelFuture future = ctx.channel().writeAndFlush("hello");
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("======");
                if (future.isSuccess()) {
                    Thread.sleep(5000);
                    System.out.println("======isSuccess");
                }
            }
        });

        System.out.println("继续执行，不阻塞");*/

        new Thread(new ThreadTep(ctx.channel())).start();

        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       /* try {
            System.out.println(1/0);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.pipeline().fireExceptionCaught(e);
        }*/
        // ctx.pipeline().fireChannelRead("a");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("ChannelInboundHandlerAdapter3  exceptionCaught");
        //如果这么写不就是死循环了嘛 ，传递到下面去了之后又传递过来又执行，反复执行了
        //ctx.pipeline().fireExceptionCaught(cause);

        ctx.fireExceptionCaught(cause);
    }


    class ThreadTep implements Runnable{
        private Channel channel;

        public ThreadTep() {
        }

        public ThreadTep(Channel channel) {
            this.channel = channel;
        }

        public void run() {
            ChannelFuture future = channel.writeAndFlush("hello");
            future.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) throws Exception {
                    System.out.println("======");
                    if (future.isSuccess()) {
                        Thread.sleep(5000);
                        System.out.println("======isSuccess");
                    }
                }
            });

            System.out.println("继续执行，不阻塞");
        }
    }
}