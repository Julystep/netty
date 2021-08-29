import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class NettyTest {

    public static void main(String[] args) {
        Channel channel = new NioSocketChannel();
        EventLoop eventLoop = new DefaultEventLoop();
        eventLoop.register(channel);
        ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
        ChannelFuture future = channel.writeAndFlush(buf);
        future.addListener((ChannelFutureListener) future1 -> {
            if (future1.isSuccess()) {
                System.out.println("write Successful");
            } else {
                System.out.println("write Error");
                future1.cause().printStackTrace();
            }
        });
    }

}
