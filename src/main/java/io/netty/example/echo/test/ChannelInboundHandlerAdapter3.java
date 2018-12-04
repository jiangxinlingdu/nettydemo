package io.netty.example.echo.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ChannelInboundHandlerAdapter3 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ChannelInboundHandlerAdapter3");
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
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("ChannelInboundHandlerAdapter3  exceptionCaught");
        //如果这么写不就是死循环了嘛 ，传递到下面去了之后又传递过来又执行，反复执行了
        //ctx.pipeline().fireExceptionCaught(cause);

        ctx.fireExceptionCaught(cause);
    }
}