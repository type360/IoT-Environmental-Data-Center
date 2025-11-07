package com.briup.smartlabs.service;

import com.briup.smartlabs.bean.ex.Device;
import com.github.pagehelper.PageInfo;

public interface SmartDeviceService {
	PageInfo<Device> findByCondition(
		int pageNum,int pageSize,String typeId,String key);
	
	void sendMessage(String command);
}
