package com.shentu.nio.channelHanndel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;;


public class MyChannelHandel extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel is activated");
        ByteBuf bf = Unpooled.copiedBuffer("hello".getBytes());
        ctx.writeAndFlush(bf);
//        final ChannelFuture f = ctx.writeAndFlush(Unpooled.copiedBuffer("HelloNetty".getBytes()));
//        f.addListener(new ChannelFutureListener() {
//
//            public void operationComplete(ChannelFuture future) throws Exception {
//                System.out.println("msg send!");
//                //ctx.close();
//            }
//        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf buf = (ByteBuf)msg;
            System.out.println(buf.toString());
            System.out.println(buf.toString(CharsetUtil.UTF_8));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
