package com.briup.smartlabs.service;

import com.briup.smartlabs.bean.ex.DeviceType;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface DeviceTypeService {
	void saveOrUpdate(DeviceType type);
	
	void removeById(String id);

	Map<String,List<String>> removeByBatch(String[] id);

	PageInfo<DeviceType> findByCondition(
			int pageSize, int pageNum, String key);
}
