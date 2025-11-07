package com.briup.smartlabs.service;

import com.briup.smartlabs.bean.SmartLabs;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface LabsService {

	void saveOrUpdate(SmartLabs labs);

	Map<String, List<String>> removeByBatch(List<String> ids);

	PageInfo<SmartLabs> findByCondition(int pageNum, int pageSize, String key);
}
