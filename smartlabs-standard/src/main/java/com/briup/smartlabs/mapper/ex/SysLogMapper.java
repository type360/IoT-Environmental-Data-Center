package com.briup.smartlabs.mapper.ex;

import com.briup.smartlabs.bean.ex.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
public interface SysLogMapper {
	
	void insert(SysLog log);
	
	List<SysLog> findByCondition(
			@Param("begin")Date beginDate,
			@Param("end")Date endDate,
			@Param("key")String key);
}





