package com.briup.smartlabs.common.syslog.util;

import com.briup.smartlabs.bean.ex.SysLog;

public class LogHolder {
	//ThreadLocal用来给线程绑定线程变量
	private static final ThreadLocal<SysLog> logHolder = 
			new ThreadLocal<>();
	
	public static void setLog(SysLog log) {
		logHolder.set(log);
	}

	public static SysLog getLog() {
		return logHolder.get();
	}

	public static void clear() {
		logHolder.set(null);
	}
}
