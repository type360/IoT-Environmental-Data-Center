package com.briup.smart.env.client;

import com.briup.smart.env.entity.Environment;
import org.apache.log4j.Logger;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author 86151
 * @program: IoT-Environmental-Data-Center
 * @description:数据发送
 * @create 2025/11/09 14:47
 **/
public class ClientImpl implements Client {
    //传输的流是字节还是字符  输入还是输出   方向Socket(网络写，上一个是从文件写) |   File | ByteArray |  pipe
    Logger logger = Logger.getRootLogger();
    @Override
    public void send(Collection<Environment> c) throws Exception {
        try ( Socket s = new Socket("localhost", 9999);
              ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());/*s.getOutputStream()只能传输基本的数据，不能传输对象,这里包装了一下才使其可以传输对象*/){
            //将集合写到网络中,服务端在网络中接收到这些数据
            logger.info("客户端" + s.getPort()+ "准备发送数据");
            TimeUnit.SECONDS.sleep(60);
            oos.writeObject(c);
            oos.flush();
            logger.info("发送数据完毕");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
