package com.briup.smartlabs.web.controller;

import com.briup.smartlabs.bean.ex.MenuEx2;
import com.briup.smartlabs.bean.ex.SysMenuEx;
import com.briup.smartlabs.common.utils.Response;
import com.briup.smartlabs.service.SysMenuService;
import com.briup.smartlabs.web.vm.NavMenuVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "系统菜单服务")
@RestController
@RequestMapping("/sys")
public class SysMenuController {
	
	@Autowired
	SysMenuService menuService;

	@ApiOperation(value = "获取当前登录用户的菜单项")
	@GetMapping("/nav")
	public Response<List<NavMenuVM>> getNavByCurrentUser() {
		return Response.ok(
				menuService.getNavMenuOfCurrentUser());
	}
	
	@ApiOperation(value = "获取的是权限列表树(菜单树)")
	@GetMapping("/menuTree")
	public Response<List<SysMenuEx>> getAllMenuTree(){
		
		return Response.ok(menuService.findMenuTree());
	}
	
	@ApiOperation(value = "根据父id查找所有子菜单")
	@ApiImplicitParam(name = "parentId",value = "上级菜单id",defaultValue = "0",required = true)
	@GetMapping("/menu/by/parent")
	public Response<List<MenuEx2>> getMenuByParentId(
		@RequestParam(defaultValue = "0")long parentId){
		List<MenuEx2> list = menuService.findByParent(parentId);
		return Response.ok(list);
	}
}




