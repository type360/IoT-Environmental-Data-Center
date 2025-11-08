package com.briup.smart.env.main;

import com.briup.smart.env.client.Gather;
import com.briup.smart.env.client.GatherImpl;
import com.briup.smart.env.entity.Environment;

import java.util.Collection;

/**
 * @author 86151
 * @program: IoT-Environmental-Data-Center
 * @description
 * @create 2025/11/08 22:27
 **/
public class Client {
    public static void main(String[] args) throws Exception {
        Gather g = new GatherImpl();
        Collection<Environment> gather = g.gather();
    }
}
