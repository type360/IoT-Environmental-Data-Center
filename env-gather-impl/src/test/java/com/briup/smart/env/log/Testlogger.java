package com.briup.smart.env.log;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author 86151
 * @program: IoT-Environmental-Data-Center
 * @description
 * @create 2025/11/10 16:05
 **/
public class Testlogger {
    //获取日志对象
    Logger logger = Logger.getRootLogger();
    @Test
    public void logger() {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

    }
}
