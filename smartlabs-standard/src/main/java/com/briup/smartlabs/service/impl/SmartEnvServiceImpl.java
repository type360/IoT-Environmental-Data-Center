package com.briup.smartlabs.service.impl;

import com.briup.smartlabs.bean.SmartEnvDetail;
import com.briup.smartlabs.bean.ex.EnvStatisticData;
import com.briup.smartlabs.mapper.SmartEnvDetailMapper;
import com.briup.smartlabs.mapper.ex.EnvStatisticMapper;
import com.briup.smartlabs.service.SmartEnvService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SmartEnvServiceImpl implements SmartEnvService {
	@Autowired
	private EnvStatisticMapper statisticMapper;
	
	@Autowired
	private SmartEnvDetailMapper envDetailMapper;

	@Override
	public List<EnvStatisticData> findByMonth(String date) {
		System.out.println("date:"+date+"====="+StringUtils.isEmpty(date));

		if(StringUtils.isEmpty(date)) {
			Calendar c = Calendar.getInstance();
			date = (1970+c.get(Calendar.YEAR))+"-"+c.get(Calendar.MONTH);
			System.out.println(date);
		}
		return statisticMapper.byMonth(date);
	}
	
	@Override
	public PageInfo<SmartEnvDetail> findAllByPage(
			int pageNum,int pageSize,Date start, Date end, String type) {
		PageHelper.startPage(pageNum, pageSize, true);
		List<SmartEnvDetail> list =
				envDetailMapper.findAllByCondition(start, end, type);
		return new PageInfo<>(list);
	}

	@Override
	public List<EnvStatisticData> findByYear(String year) {
		if(StringUtils.isEmpty(year)) {
			Calendar c = Calendar.getInstance();
			year = (1970+c.get(Calendar.YEAR))+"";
		}
		return statisticMapper.byYear(year);
	}

}


