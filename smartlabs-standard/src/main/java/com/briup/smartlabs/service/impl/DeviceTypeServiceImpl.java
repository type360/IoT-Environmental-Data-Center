package com.briup.smartlabs.service.impl;

import com.briup.smartlabs.bean.ex.DeviceType;
import com.briup.smartlabs.common.exceptions.DataValidateException;
import com.briup.smartlabs.mapper.ex.DeviceMapper;
import com.briup.smartlabs.mapper.ex.DeviceTypeMapper;
import com.briup.smartlabs.service.DeviceTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Servie标注的类
 * 		Spring会自动创建一个这种类型的对象，并且维护在容器中。
 * 			ApplicationContext ac; spring的容器
 * 			ac.put("deviceTypeServiceImpl",new DeviceTypeServieImpl());
 * @author guomiao
 *
 */
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {
	
	@Autowired
	DeviceTypeMapper deviceTypeMapper;
	
	@Autowired
	DeviceMapper deviceMapper;
	
	@Transactional
	public void saveOrUpdate(DeviceType type) {
		//判断如果用户输入的设备类型id不为空，代表需要执行更新操作
		if(type.getId()!=null&&!"".equals(type.getId())) {
			deviceTypeMapper.updateById(type);
		}else {
			//否者，参数中没有设备类型的id，代表执行保存操作。
			type.setId(UUID.randomUUID().toString());//生成唯一且不重复的id值
			deviceTypeMapper.insert(type);
		}
	}
	
	@Transactional
	@Override
	public void removeById(String id) {
		if(id==null||"".equals(id)) {
			throw new DataValidateException("id不能为空！");
		}
		/*
		 * TODO
		 * 	1. 如果删除设备类型时，还有设备和这个类型相关联，提示用户无法删除。
		 * 		按照类型id，从设备表中查找，是否有设备和其有关联
		 *  2. 判断如果没有关联的设备，允许删除设备类型。
		 */
		//删除设备类型之前，先将关联的设备删除
		deviceMapper.deleteByDeviceType(id);
		//从数据库中删除指定id所对应的设备类型
		int count = deviceTypeMapper.deleteById(id);
		if(count == 0) {
			throw new DataValidateException("设备类型不存在！");
		}
		
	}

	//原始数据【1，2，3，4，5】
	//数据库中【2，3，5】
	@Transactional
	@Override
	public Map<String,List<String>> removeByBatch(String[] id) {
		//1.先判断数据是否存在
		if(id==null || id.length==0) {
			throw new DataValidateException("请至少选择一个！");
		}

		//判断传递过来的id是否在数据库中存在。返回所有存在的设备类型id
		//判断哪些id是数据库中存在的，哪些不存在
		List<String> exitIds = deviceTypeMapper.existById(id);

		//缺代码：根据typeId下所有设备

		//批量删除所有类型
		if(exitIds!=null&&exitIds.size()>0) {
			String[] ids = new String[exitIds.size()];
			exitIds.toArray(ids);
			//从数据库中删除所有存在的设备类型
			deviceTypeMapper.deleteByBatch(ids);

		}

		// 挑出所有不存在的设备类型id
		// 如果数据库中存在的设备类型id不为空
		List<String> noExistIds = new ArrayList<>();
		for(String val : id) {
			if(!exitIds.contains(val)) {
				noExistIds.add(val);
			}
		}

		System.out.println("存在的id:"+exitIds);
		System.out.println("不存在id："+noExistIds);

		Map<String, List<String>> map = new HashMap<>();
		map.put("success", exitIds);
		map.put("error", noExistIds);

		return map;
	}

	@Transactional(readOnly = true)
	@Override
	public PageInfo<DeviceType> findByCondition(
			int pageSize, int pageNum, String key) {

		//准备分页，第三个参数为true代表要执行select count操作。
//		if(key!=null&&!"".equals(key)) {
//			key = "%"+key+"%";
//		}

		PageHelper.startPage(pageNum, pageSize,true);
		List<DeviceType> list = 
				deviceTypeMapper.selectByCondition(key);
		if(list==null) {
			list = new ArrayList<>();
		}
		return new PageInfo<DeviceType>(list);
	}
}





