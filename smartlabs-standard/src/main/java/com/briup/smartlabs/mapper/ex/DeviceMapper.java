package com.briup.smartlabs.mapper.ex;

import com.briup.smartlabs.bean.ex.Device;

import java.util.List;

public interface DeviceMapper {
	/**
	 *  按照设备类型删除设备
	 * @author guomiao
	 *
	 * @param typeId 设备类型编号
	 * @return
	 */
	int deleteByDeviceType(String typeId);
	//将指定实验室中所有设备进行解绑。
	int unbindDeviceByLabsId(List<String> labsId);
	/**
	 * 
	 * @author guomiao
	 *
	 * @param typeId 设备类型编号
	 * @param key	关键字（设备名称、类型名称，设备地址）
	 * @return
	 */
	List<Device> findByCondition(String typeId,String key);
}



