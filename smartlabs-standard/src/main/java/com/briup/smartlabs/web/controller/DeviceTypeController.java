package com.briup.smartlabs.web.controller;

import com.briup.smartlabs.bean.ex.DeviceType;
import com.briup.smartlabs.common.exceptions.DataValidateException;
import com.briup.smartlabs.common.utils.Response;
import com.briup.smartlabs.common.utils.ResponseCodeEnum;
import com.briup.smartlabs.service.DeviceTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
 * 所有和设备类型相关的服务。
 */
//@RestController
//@RequestMapping(value = "/deviceType")
public class DeviceTypeController {
	/*
	 * @Autowired 
	 * 		相当于在跟Spring容器要数据。
	 * 		impl = ac.getBean(DeviceTypeService.class);
	 */
	@Autowired
	DeviceTypeService impl;  //spring ioc容器
	
	@GetMapping(value = "/page/by/condition")
	public Response<PageInfo<DeviceType>> findByCondition(
			@RequestParam(defaultValue = "10")int pageSize,
			@RequestParam(defaultValue = "1")int pageNum,
			String key){
		System.out.println("pageSize : "+pageSize);
		System.out.println("pageNum :"+pageNum);
		System.out.println("key : "+key);
		PageInfo<DeviceType> datas = 
			impl.findByCondition(pageSize,pageNum,key);
		return Response.ok(datas);
	}
	
	
	@DeleteMapping("/batch")
	public Response<Map<String,List<String>>> deleteByBatch(String[] id) {
		try {
			Map<String,List<String>> data = 
					impl.removeByBatch(id);
			return Response.ok(data);
		}catch(Exception e) {
			e.printStackTrace();
			return Response.error(ResponseCodeEnum.ERROR, null);
		}
	}
	
	//按照id删除设备类型
	@DeleteMapping("/{id}")
	public Response<String> deleteById(@PathVariable String id) {
		try {
			impl.removeById(id);
		}catch (DataValidateException e) {
			e.printStackTrace();
			return Response.error(ResponseCodeEnum.DATA_WRONG,
					e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return Response.error(ResponseCodeEnum.ERROR,null);
		}
		return Response.ok("删除设备【"+id+"】成功！");
	}
	
	//保存或更新设备类型服务
	@PostMapping("/saveOrUpdate")
	public Response<String> addType(@RequestBody DeviceType type) {
		System.out.println("请求参数type:"+type);
		try {
			type.setId(UUID.randomUUID().toString());
			impl.saveOrUpdate(type);
		}catch (Exception e) {
			e.printStackTrace();
			return Response.error(ResponseCodeEnum.ERROR, e.getMessage());
		}
		return Response.ok("操作成功！");
	}
	
}








