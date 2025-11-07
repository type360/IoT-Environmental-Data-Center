package com.briup.smartlabs.web.controller;

import com.briup.smartlabs.bean.SmartEnvDetail;
import com.briup.smartlabs.bean.ex.EnvStatisticData;
import com.briup.smartlabs.common.utils.Response;
import com.briup.smartlabs.service.SmartEnvService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

//统计模块
@Api(tags = "环境数据服务")
@RestController
@RequestMapping("/env")
public class SmartEnvController {
	
	@Autowired
	SmartEnvService envService;
	
	@ApiOperation(value = "按月统计(指定实验环境数据)")
	@GetMapping("/statistic/by/month")
	public Response<List<EnvStatisticData>> statisByMonth(String date){
		return Response.ok(envService.findByMonth(date));
	}
	
	@ApiOperation(value = "按年统计(指定实验环境数据)")
	@GetMapping("/statistic/by/year")
	public Response<List<EnvStatisticData>> statisByYear(String year){
		return Response.ok(envService.findByYear(year));
	}

	@ApiOperation(value = "分页显示监测数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", required = true, defaultValue = "1"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int", required = true, defaultValue = "5"),
			@ApiImplicitParam(name = "startTime", value = "开始时间",paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "type", value = "数据类型", paramType = "query", dataType = "String")
	})
	@GetMapping("/detail/by/condition")
	public Response<PageInfo<SmartEnvDetail>> envDetail(
			Integer pageNum,
			Integer pageSize,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date startTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date endTime,
			String type){
		System.out.println("pageNum: " + pageNum + " pageSize:" + pageSize);
		System.out.println("start: " + startTime + " end: " + endTime);
		System.out.println("type: " + type);

		PageInfo<SmartEnvDetail> page =
				envService.findAllByPage(pageNum, pageSize, startTime, endTime, type);
		return Response.ok(page);
	}
}





