package com.briup.smartlabs.mapper;

import com.briup.smartlabs.bean.SysMenu;

import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(Long menuId);

    List<SysMenu> selectAll();

    int updateByPrimaryKey(SysMenu record);
}