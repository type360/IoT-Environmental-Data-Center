package com.briup.smartlabs.web.controller;

import com.briup.smartlabs.bean.SmartLabs;
import com.briup.smartlabs.common.utils.Response;
import com.briup.smartlabs.service.LabsService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "实验室信息服务")
@RestController
@RequestMapping("/labs")
public class LabsController {
	
	@Autowired
	LabsService labsService;
	
	@ApiOperation(value = "按条件分页检索",
			notes = "关键字可以是实验室名称，标识，网络地址")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum",value = "当前页码",
					dataType = "int",paramType = "query",
					defaultValue = "1"),
		@ApiImplicitParam(name = "pageSize",value = "分页大小",
					defaultValue = "10"),
		@ApiImplicitParam(name = "key",value = "实验室名称/标识/网络地址")
	})
	@GetMapping("/by/condition")
	public Response<PageInfo<SmartLabs>> findByCondition(
		@RequestParam(defaultValue = "1")int pageNum,
		@RequestParam(defaultValue = "10")int pageSize,String key){
		PageInfo<SmartLabs> page = 
				labsService.findByCondition(pageNum,pageSize,key);
		return Response.ok(page);
	}
	
	
	@ApiOperation(value = "按照id批量删除实验室信息",notes = "批量删除实验室信息！")
	@DeleteMapping("/batch")
	public Response<Map<String, List<String>>> removeByBatch(
			@RequestParam(name = "ids")List<String> ids){
		Map<String,List<String>> result = 
				labsService.removeByBatch(ids);
		return Response.ok(result);
	}
	
	@ApiOperation(value = "保存或更新实验室信息",
			notes = "如果实验室id为空，保存！<br/>"
					+ "如果实验室id不为空，更新！")
	@PostMapping("/lab")
	public Response<String> saveOrUpdate(
			@RequestBody SmartLabs labs){
		String msg = 
				ObjectUtils.isEmpty(labs.getId())?"保存成功！":"更新成功！";
		labsService.saveOrUpdate(labs);
		return Response.ok(msg);
	}
}









