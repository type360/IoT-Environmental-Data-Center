package com.briup.smartlabs.service;

import com.briup.smartlabs.bean.ex.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.Date;

public interface SysLogService {
	/**
	 * 按照条件查找日志记录
	 * @author guomiao
	 *
	 * @param pageNum 当前页码
	 * @param pageSize	页面大小
	 * @param begin		开始日期
	 * @param end		结束日期
	 * @param key		查找关键字
	 * @return
	 */
	PageInfo<SysLog> findByCondition(
			int pageNum,int pageSize,Date begin,
			Date end,String key);
}







