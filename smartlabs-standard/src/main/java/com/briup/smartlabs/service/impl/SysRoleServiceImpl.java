package com.briup.smartlabs.service.impl;

import com.briup.smartlabs.bean.SysRole;
import com.briup.smartlabs.bean.SysRoleMenu;
import com.briup.smartlabs.common.exceptions.DataValidateException;
import com.briup.smartlabs.common.utils.AuthenticationHolder;
import com.briup.smartlabs.mapper.SysRoleMapper;
import com.briup.smartlabs.mapper.SysRoleMenuMapper;
import com.briup.smartlabs.mapper.ex.RoleMenuExMapper;
import com.briup.smartlabs.mapper.ex.SysRoleExMapper;
import com.briup.smartlabs.service.SysRoleService;
import com.briup.smartlabs.web.vm.RoleParamVM;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private RoleMenuExMapper roleMenuExMapper;
	@Autowired
	private SysRoleMenuMapper roleMenuMapper;
	@Autowired
	private SysRoleExMapper roleExMapper;
	@Override
	public List<SysRole> findAll() {
		return roleMapper.selectAll();
	}
	
	@Transactional
	@Override
	public void saveOrUpdateRole(RoleParamVM vm) {
		SysRole role = null;
		if(vm.getRoleId()!=null) { //更新
			role = roleMapper.selectByPrimaryKey(vm.getRoleId());
			if(role==null) {
				throw new DataValidateException("角色不存在！");
			}
			BeanUtils.copyProperties(vm, role,"createUserId","createTime");
			roleMapper.updateByPrimaryKey(role);
			//按照角色id删除角色权限关联（角色菜单关联）
			roleMenuExMapper.deleteByRole(vm.getRoleId());
		}else {		//插入。保存
			role = new SysRole();
			BeanUtils.copyProperties(vm, role);
			role.setCreateTime(new Date());
			role.setCreateUserId(AuthenticationHolder.getCurrentUserId());
			roleMapper.insert(role);
		}
		if(vm.getMenus()!=null&&vm.getMenus().size()>0) {
			for(Long menuId:vm.getMenus()) {
				SysRoleMenu srm = new SysRoleMenu();
				srm.setRoleId(role.getRoleId());
				srm.setMenuId(menuId);
				roleMenuMapper.insert(srm);
			}
		}
	}

	@Override
	public PageInfo<RoleParamVM> findByCondition(int pageNum, int pageSize, String key) {
		Page<RoleParamVM> page = 
				PageHelper.startPage(pageNum, pageSize, true);
		key = StringUtils.isEmpty(key)?key:"%"+key+"%";
		List<SysRole> list = roleExMapper.findByCondition(key);
//		Collection<RoleParamVM> vms = EntityUtils.toList(list, RoleParamVM.class);
//		for(RoleParamVM vm:vms) {
//			vm.setMenus(roleMenuExMapper.findMenusByRole(vm.getRoleId()));
//		}
		List<RoleParamVM> vms = new ArrayList<>(list.size());
		for(SysRole role:list) {
			RoleParamVM vm = new RoleParamVM();
			BeanUtils.copyProperties(role, vm);
			vms.add(vm);
			vm.setMenus(roleMenuExMapper.findMenusByRole(role.getRoleId()));
		}
		page.clear();
		page.addAll(vms);
		return new PageInfo<>(page);
	}

}






 