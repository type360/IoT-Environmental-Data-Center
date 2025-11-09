package com.briup.smart.env.main;

import com.briup.smart.env.server.Server;
import com.briup.smart.env.server.ServerImpl;

/**
 * @author 86151
 * @program: IoT-Environmental-Data-Center
 * @description
 * @create 2025/11/09 15:56
 **/
public class ServerMain {
    public static void main(String[] args) throws Exception {
        Server server = new ServerImpl();
        server.reciver();

    }
}
