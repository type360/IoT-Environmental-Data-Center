package com.briup.smartlabs.web.controller;

import com.briup.smartlabs.bean.ex.SysLog;
import com.briup.smartlabs.common.utils.Response;
import com.briup.smartlabs.service.SysLogService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(tags = "系统日志服务")
@RestController
@RequestMapping("/syslog")
public class SysLogController {
	@Autowired
	private SysLogService logService;
	
	@ApiOperation(value = "按条件查找所有日志记录",
			notes = "按条件查找所有日志记录，并分页显示。条件包含：<br/>"
					+ "1. 开始日期 <br/>"
					+ "2. 结束日期 <br/>"
					+ "3. 关键字（用户名，方法名，操作）<br/>"
					+ "默认显示当天的日志并按照请求时间倒序排列")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum",value = "当前页码",
						 defaultValue = "1",dataType = "int"),
		@ApiImplicitParam(name = "pageSize",value = "每页个数",
						defaultValue = "10",dataType = "int"),
		@ApiImplicitParam(name = "begin",value = "开始日期(yyyy-MM-dd)",
						paramType = "query"),
		@ApiImplicitParam(name = "end",value = "结束日期(yyyy-MM-dd)",
						paramType = "query"),
		@ApiImplicitParam(name = "key",value = "关键字")
	})
	@GetMapping("/by/condition")
	public Response<PageInfo<SysLog>> findByCondition(
			@RequestParam(defaultValue = "1")int pageNum,
			@RequestParam(defaultValue = "10")int pageSize,
			@DateTimeFormat(pattern = "yyyy-MM-dd")Date begin,
			@DateTimeFormat(pattern = "yyyy-MM-dd")Date end,String key){
		PageInfo<SysLog> pageInfo = 
				logService.findByCondition(pageNum, pageSize, begin, end, key);
		return Response.ok(pageInfo);
	}
}








