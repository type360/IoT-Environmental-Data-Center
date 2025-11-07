package com.briup.smartlabs.service.impl;

import com.briup.smartlabs.bean.ex.SysLog;
import com.briup.smartlabs.mapper.ex.SysLogMapper;
import com.briup.smartlabs.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService{
	@Autowired
	private SysLogMapper logMapper;

	@Override
	public PageInfo<SysLog> findByCondition(int pageNum, int pageSize,
			Date begin, Date end, String key) {
		
		if(key!=null&&!"".equals(key.trim())) {
			key = "%"+key+"%";
		}
		PageHelper.startPage(pageNum, pageSize, true);
		List<SysLog> list = 
				logMapper.findByCondition(begin, end, key);
		if(list==null) {
			list = new ArrayList<>();
		}
		return new PageInfo<>(list);
	}

}





