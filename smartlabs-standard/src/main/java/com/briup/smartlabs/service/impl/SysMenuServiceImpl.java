package com.briup.smartlabs.service.impl;

import com.briup.smartlabs.bean.SysMenu;
import com.briup.smartlabs.bean.ex.MenuEx2;
import com.briup.smartlabs.bean.ex.SysMenuEx;
import com.briup.smartlabs.common.utils.AuthenticationHolder;
import com.briup.smartlabs.mapper.ex.SysMenuExMapper;
import com.briup.smartlabs.service.SysMenuService;
import com.briup.smartlabs.web.vm.NavMenuVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.briup.smartlabs.common.constant.SmartLabsConstants.MenuType.DIR;
import static com.briup.smartlabs.common.constant.SmartLabsConstants.MenuType.MENU;

@Service
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
	SysMenuExMapper menuExMapper;

	@Override
	public List<NavMenuVM> getNavMenuOfCurrentUser() {
		Long id = AuthenticationHolder.getCurrentUserId();
//		if(id==SmartLabsConstants.SUPER_ADMIN_ID) {
//			//获取所有菜单返回
//		}else {
//			//根据当前用户查找角色所对应的菜单。
//		}
		//zaisql语句中已经根据用户id是否=1
		List<SysMenu> list = menuExMapper.findNavMenuByUserId(id);
		List<NavMenuVM> vms = new ArrayList<>();
		if(list!=null && list.size()>0) {
			Map<Long, NavMenuVM> dirs = new TreeMap<>();
			for(int i = 0;i<list.size();i++) {
				SysMenu m = list.get(i); //获取每一个系统菜单
				//如果是目录，直接存放到vms中。
				NavMenuVM vm = convertFrom(m);	//将系统菜单转换成前端菜单数据
				if(m.getType().intValue()==DIR.getType()) { //判断该菜单是否是目录
					//System.out.println("dir id:"+m.getMenuId());
					dirs.put(vm.getId(), vm);	//如果是，放入map集合，用来和子菜单匹配
				}else if(m.getType().intValue()==MENU.getType()) { //判断是否是菜单项
					//System.out.println("menu parent id"+m.getParentId());
					NavMenuVM parent = dirs.get(m.getParentId()); //获取父目录菜单
					parent.getSubs().add(vm); //添加到父目录的subs集合中。
				}
			}
			vms.addAll(dirs.values());
		}
		return vms;
	}
	
	private NavMenuVM convertFrom(SysMenu menu) {
		NavMenuVM vm = new NavMenuVM();
		vm.setId(menu.getMenuId());
		vm.setIndex(menu.getUrl());
		vm.setIcon(menu.getIcon());
		vm.setTitle(menu.getName());
		vm.setSubs(new HashSet<NavMenuVM>());
		return vm;
	}

	@Override
	public List<SysMenuEx> findMenuTree() {
		return menuExMapper.findMenuTree();
	}

	@Override
	public List<MenuEx2> findByParent(long parentId) {
		return menuExMapper.selectMenuByParent(parentId);
	}
}






