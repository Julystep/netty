import io.netty.buffer.*;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

public class ByteBufTest {

    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
        ByteBuf byteBuf = new UnpooledDirectByteBuf(allocator, 1024, 2048);
        byteBuf.writeBytes("this is test\r".getBytes());
        System.out.println(byteBuf.writerIndex());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.forEachByte(ByteProcessor.FIND_CR));
        ByteBuf sameByteBuf = byteBuf.slice(0, 3);
        System.out.println(sameByteBuf.toString(CharsetUtil.UTF_8));
        // 读出来想要的元素
        UnpooledByteBufAllocator allocator2 = new UnpooledByteBufAllocator(false);
        ByteBuf cachedByteBuf = new UnpooledDirectByteBuf(allocator2, 1024, 2048);
        byteBuf.readBytes(cachedByteBuf, 12);
        System.out.println(cachedByteBuf.writerIndex());
        System.out.println(cachedByteBuf.readerIndex());
    }

    private static void test2() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("this is test\r", CharsetUtil.UTF_8);
        ByteBuf sliced = byteBuf.copy(0, 4);
//        ByteBuf sliced = byteBuf.slice(0, 4);
        System.out.println(sliced.toString(CharsetUtil.UTF_8));
        sliced.setByte(2, (byte) 'a');
        sliced.setByte(3, (byte) 't');
        System.out.println(sliced.toString(CharsetUtil.UTF_8));
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
    }

    private static void test3() {
        
    }

}
