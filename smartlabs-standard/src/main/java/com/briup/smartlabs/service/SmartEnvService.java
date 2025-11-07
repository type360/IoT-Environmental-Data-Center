package com.briup.smartlabs.service;

import com.briup.smartlabs.bean.SmartEnvDetail;
import com.briup.smartlabs.bean.ex.EnvStatisticData;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface SmartEnvService {
	public List<EnvStatisticData> findByMonth(String date);

	public PageInfo<SmartEnvDetail> findAllByPage(
			int pageNum,int pageSize,Date start,Date end,String type);

	public List<EnvStatisticData> findByYear(String year);
}
