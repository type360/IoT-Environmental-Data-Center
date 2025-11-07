package com.briup.smartlabs.web.controller;

import com.briup.smartlabs.bean.ex.SysUserEx;
import com.briup.smartlabs.common.utils.Response;
import com.briup.smartlabs.service.SysUserService;
import com.briup.smartlabs.web.vm.UserParamVM;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api(tags = "用户服务")
@RestController
@RequestMapping("/user")
public class SysUserController {
	@Autowired
	private SysUserService userService;
	
	@ApiOperation(value = "按照用户编号删除用户信息")
	@ApiImplicitParam(name = "id",value = "用户编号",
								paramType = "path",required = true)
	@DeleteMapping(value = "/{id}")
	public Response<String> deleteById(
			@PathVariable("id")Long id){
		userService.deleteById(id);
		return Response.ok("成功删除用户:"+id);
	}
	
	@ApiOperation(value = "分页查找所有用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum",value = "页码",
				defaultValue = "1"),
		@ApiImplicitParam(name = "pageSize",value = "每页显示个数",
				defaultValue = "5")
	})
	@GetMapping("/all")
	public Response<PageInfo<SysUserEx>> allByPage(
			@RequestParam(defaultValue = "1")int pageNum,
			@RequestParam(defaultValue = "5")int pageSize){
		
		return Response.ok(
				userService.findAllByPage(
							pageNum, pageSize));
	}

	@ApiOperation(value = "保存或更新用户信息")
	@PostMapping
	public Response<String> saveOrUpdateUser(@RequestBody UserParamVM user){
		userService.saveOrUpdate(user);
		return Response.ok("保存用户："+user);
	}
	
	@ApiOperation(value = "批量删除用户信息")
	@DeleteMapping("/batch")
	public Response<String> deleteByIds(@RequestParam Set<Long> ids){
		userService.deleteByBatch(ids);
		return Response.ok("删除成功！");
	}
	
}











