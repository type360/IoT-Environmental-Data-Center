package com.briup.smartlabs.service;

import com.briup.smartlabs.bean.ex.MenuEx2;
import com.briup.smartlabs.bean.ex.SysMenuEx;
import com.briup.smartlabs.web.vm.NavMenuVM;

import java.util.List;

public interface SysMenuService {

	public List<NavMenuVM> getNavMenuOfCurrentUser();
	
	public List<SysMenuEx> findMenuTree();

	public List<MenuEx2> findByParent(long parentId);
}
