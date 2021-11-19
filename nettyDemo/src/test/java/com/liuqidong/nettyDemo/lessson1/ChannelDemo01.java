package com.liuqidong.nettyDemo.lessson1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class ChannelDemo01 {
    public static void main(String[] args) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("data.txt", "rw")) {
            FileChannel channel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(16);

            while (true) {
                //读取数据到buffer
                int len = channel.read(byteBuffer);
                log.info("读取字节数:{}", len);
                //没有数据直接跳出循环
                if (len == -1) {
                    break;
                }
                //切换读模式
                byteBuffer.flip();
                //判断是否还有数据
                while (byteBuffer.hasRemaining()) {
                    log.info("读取字节数据:{}", (char) byteBuffer.get());
                }

                //切换buffer写模式
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
