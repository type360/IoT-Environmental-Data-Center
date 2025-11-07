package com.briup.smartlabs.web.controller;

import com.briup.smartlabs.bean.SysRole;
import com.briup.smartlabs.common.utils.Response;
import com.briup.smartlabs.service.SysRoleService;
import com.briup.smartlabs.web.vm.RoleParamVM;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色服务")
@RestController
@RequestMapping("/role")
public class SysRoleController {
	@Autowired
	private SysRoleService roleService;

	@ApiOperation(value = "查找所有角色，不分页")
	@GetMapping("/all")
	public Response<List<SysRole>> findAll(){
		return Response.ok(roleService.findAll());
	}
	
	@ApiOperation(value = "保存或更新角色信息")
	@PostMapping
	public Response<String> saveOrUpdateRole(
			@RequestBody RoleParamVM vm){
		String msg = ObjectUtils.isEmpty(vm.getRoleId())?
						"保存成功！":"更新成功！";
		System.out.println("页面参数:"+vm);
		roleService.saveOrUpdateRole(vm);
		return Response.ok(msg);
	}
	
	@ApiOperation(value = "按条件分页查找角色列表")
	@GetMapping("/by/condition")
	public Response<PageInfo<RoleParamVM>> findByCondition(
			int pageNum,int pageSize,String key){
		PageInfo<RoleParamVM> list = 
				roleService.findByCondition(pageNum,pageSize,key);
		return Response.ok(list);
	}
}






