package com.briup.smartlabs.web.controller;

import com.briup.smartlabs.bean.ex.DeviceType;
import com.briup.smartlabs.common.utils.Response;
import com.briup.smartlabs.service.DeviceTypeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
 * 所有和设备类型相关的服务
 */
@Api(tags = "设备类型服务")
@RestController
@RequestMapping(value = "/deviceType")
public class DeviceTypeWithoutExceptionController {
	/*
	 * @Autowired 
	 * 		相当于在跟Spring容器要数据。
	 * 		impl = ac.getBean(DeviceTypeService.class);
	 */
	@Autowired
	private DeviceTypeService impl;  //spring ioc容器
	
	@CrossOrigin(originPatterns = "*",allowedHeaders = "*")
	@ApiOperation(value = "按照条件进行分页查找",
			notes = "按照关键字进行分页查找<br/>"
					+ "关键字可以是设备类型名")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageSize",value = "每页显示的数据个数",
				allowableValues = "5,10,20,100",defaultValue = "10",
				dataType = "int"),
		@ApiImplicitParam(name = "pageNum",value = "要显示第几页数据",
				defaultValue = "1",dataType = "int"),
		@ApiImplicitParam(name = "key",value = "查找关键字(指的是类型名)")
	})
	@GetMapping(value = "/page/by/condition")
	public Response<PageInfo<DeviceType>> findByCondition(
			@RequestParam(defaultValue = "10")int pageSize,
			@RequestParam(defaultValue = "1")int pageNum,String key){
		//创建SysLog对象，id/username/operation/method/params/createDate
		PageInfo<DeviceType> datas = 
			impl.findByCondition(pageSize,pageNum,key);
		
		//将SysLog写入到Database
		return Response.ok(datas);
	}
	
	@DeleteMapping("/batch")
	public Response<Map<String,List<String>>> deleteByBatch(String[] id) {
			Map<String,List<String>> data = impl.removeByBatch(id);
			return Response.ok(data);
	}
	
	
	//按照id删除设备类型
	@ApiOperation(value = "按照设备类型编号进行删除",
			notes = "按照设备类型编号，删除设备类型<br/>"
					+ "同时将关联的设备进行解绑。")
	@ApiImplicitParam(name="id",value = "设备类型的编号",
				required = true)
	@DeleteMapping("/{id}")
	public Response<String> deleteById(@PathVariable String id) {
			impl.removeById(id);
		return Response.ok("删除设备【"+id+"】成功！");
	}
	
	//@CrossOrigin(originPatterns = "*",allowedHeaders = "*")
	@ApiOperation(value = "保存或更新设备类型",
			notes = "如果参数中不包含id，保存<br/>"
					+ "如果参数中包含id，更新")
	//保存或更新设备类型服务
	@PostMapping("/saveOrUpdate")
	public Response<String> addType(@RequestBody DeviceType type) {
			impl.saveOrUpdate(type);
		return Response.ok("操作成功！");
	}
	
}








