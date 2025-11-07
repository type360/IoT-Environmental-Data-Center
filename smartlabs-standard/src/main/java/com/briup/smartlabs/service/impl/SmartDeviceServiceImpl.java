package com.briup.smartlabs.service.impl;

import com.briup.smartlabs.bean.ex.Device;
import com.briup.smartlabs.mapper.ex.DeviceMapper;
import com.briup.smartlabs.service.SmartDeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class SmartDeviceServiceImpl implements SmartDeviceService {
	@Autowired
	private DeviceMapper deviceExMapper;

	@Override
	public PageInfo<Device> findByCondition(int pageNum, int pageSize, String typeId, String key) {
		
		key = ObjectUtils.isEmpty(key)?key:"%"+key+"%";
		
		PageHelper.startPage(pageNum, pageSize,true);
		List<Device> list = 
				deviceExMapper.findByCondition(typeId, key);
		return new PageInfo<>(list);
	}

	@Override
	public void sendMessage(String command) {
		//获取设备所属实验室的ip和端口
		try(Socket socket = new Socket("localhost",9999);
			PrintStream ps = new PrintStream(
					socket.getOutputStream())){
			ps.println(command);
			ps.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}





