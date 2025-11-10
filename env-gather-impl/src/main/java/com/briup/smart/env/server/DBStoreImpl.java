package com.briup.smart.env.server;

import com.briup.smart.env.entity.Environment;

import java.util.Collection;
//1依赖：mysql驱动和du
/**
 * @author 86151
 * @program: IoT-Environmental-Data-Center
 * @description
 * @create 2025/11/10 10:39
 **/
public class DBStoreImpl implements DBStore{
    @Override
    public void saveDB(Collection<Environment> c) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");


    }
}
