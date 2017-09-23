package org.x1.utils.net.manager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.x1.utils.net.model.Request;
import org.x1.utils.SpringUtils;

public class ServerHandlerManager extends SimpleChannelInboundHandler<Request>{
	/**消息分发器*/
	private MassegeOutManager massegeHandOut = SpringUtils.getBean(MassegeOutManager.class);
	private void process(Channel channel,Request request) throws CloneNotSupportedException {
		massegeHandOut.process(channel,request);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//客户端在
		System.out.println("用户进来"+ctx.channel().remoteAddress());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
		//将消息发送到消息分发
		process(ctx.channel(), request);
	}

}