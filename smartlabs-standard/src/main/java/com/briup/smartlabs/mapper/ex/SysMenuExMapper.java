package com.briup.smartlabs.mapper.ex;

import com.briup.smartlabs.bean.SysMenu;
import com.briup.smartlabs.bean.ex.MenuEx2;
import com.briup.smartlabs.bean.ex.SysMenuEx;

import java.util.List;

public interface SysMenuExMapper {
	//根据用户名 获取其
	List<SysMenu> findMenuByUsername(String username);

	//获取用户的导航菜单
	List<SysMenu> findNavMenuByUserId(Long id);
	
	List<SysMenuEx> findMenuTree();

	List<MenuEx2> selectMenuByParent(long parentId);
}
