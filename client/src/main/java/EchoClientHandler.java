import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ChannelFuture channelFuture = ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (channelFuture.isSuccess()) {
                System.out.println("write Successful");
            } else {
                System.out.println("write Error");
                channelFuture.cause().printStackTrace();
            }
        });
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        System.out.println("Client received:" + msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
