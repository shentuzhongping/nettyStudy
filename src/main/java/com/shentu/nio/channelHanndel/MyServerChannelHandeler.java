package com.shentu.nio.channelHanndel;

import com.shentu.nio.NettyServer;
import com.sun.security.ntlm.Server;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

;


public class MyServerChannelHandeler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        System.out.println("server: channel read");
        ByteBuf buf = (ByteBuf)msg;
//        buf.refCnt();

        System.out.println(buf.toString(CharsetUtil.UTF_8));

        NettyServer.channels.writeAndFlush(msg);

//        ctx.close();

        //buf.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        NettyServer.channels.remove(ctx.channel());
        ctx.close();
    }
}
