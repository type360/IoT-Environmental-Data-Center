package com.briup.smartlabs.web.controller;

import com.briup.smartlabs.bean.ex.Device;
import com.briup.smartlabs.common.utils.Response;
import com.briup.smartlabs.service.SmartDeviceService;
import com.briup.smartlabs.web.vm.CommandVM;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "设备服务")
@RestController
@RequestMapping("/device")
public class DeviceController {
	@Autowired
	private SmartDeviceService deviceService;
	
	@ApiOperation(value = "按条件分页查找",
			notes ="可以按照设备类型精确查找<br/>"
					+ "关键字key代表可以按照设备名称，设备类型名，地址模糊匹配" )
	@ApiImplicitParams({
		@ApiImplicitParam(value = "页码",name = "pageNum",defaultValue = "1"),
		@ApiImplicitParam(value = "页面大小",name = "pageSize",defaultValue = "10"),
		@ApiImplicitParam(value = "设备类型编号",name = "typeId"),
		@ApiImplicitParam(value = "关键字(设备名/类型名/地址)",name = "key")		
	})
	@GetMapping("/by/condition")
	public Response<PageInfo<Device>> findByCondition(
			@RequestParam(defaultValue = "1")int pageNum,
			@RequestParam(defaultValue = "10")int pageSize,
			String typeId,String key){
		
		return Response.ok(deviceService.findByCondition(
					pageNum, pageSize, typeId, key));
	}
	
	@ApiOperation(value = "发送指令，控制设备运行")
	@PostMapping("/sendCommand")
	public Response<String> sendCommand(CommandVM command){
		deviceService.sendMessage(command.toString());
		return Response.ok("操作成功！");
	}
}






