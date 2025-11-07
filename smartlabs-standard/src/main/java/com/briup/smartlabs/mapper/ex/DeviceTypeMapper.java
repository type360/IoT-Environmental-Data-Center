package com.briup.smartlabs.mapper.ex;

import com.briup.smartlabs.bean.ex.DeviceType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceTypeMapper {
	int insert(DeviceType type);
	int updateById(DeviceType type);
	int deleteById(String id);

	void deleteByBatch(@Param("ids")String[] id);

	//从数据库中按照id查找所有存在的设备类型的id
	List<String> existById(@Param("ids")String[] id);
	List<DeviceType> selectByCondition(String key);
}





