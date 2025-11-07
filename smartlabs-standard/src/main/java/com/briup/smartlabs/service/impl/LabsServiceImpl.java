package com.briup.smartlabs.service.impl;

import com.briup.smartlabs.bean.SmartLabs;
import com.briup.smartlabs.common.exceptions.DataValidateException;
import com.briup.smartlabs.mapper.SmartLabsMapper;
import com.briup.smartlabs.mapper.ex.DeviceMapper;
import com.briup.smartlabs.mapper.ex.SmartLabsExMapper;
import com.briup.smartlabs.service.LabsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class LabsServiceImpl implements LabsService {
	@Autowired
	SmartLabsMapper labsMapper;
	
	@Autowired
	SmartLabsExMapper labsExMapper;
	
	@Autowired
	DeviceMapper deviceMapper;
	
	@Transactional
	@Override
	public void saveOrUpdate(SmartLabs labs) {
		//判断实验室标识是否存在（唯一）
		SmartLabs slb = 
				labsExMapper.findByLabsNum(
						labs.getLabsNum(),labs.getId());
		if(slb!=null) {
			throw new DataValidateException("实验室标识已存在！");
		}

		//判断实验室ip地址 是否存在
		slb = labsExMapper.findByNetAdres(
					labs.getNetAdres(),labs.getId());
		if(slb!=null) {
			throw new DataValidateException("网络地址已经被占用！");
		}

		//判断id为空，保存
		if(ObjectUtils.isEmpty(labs.getId())) {
			//id是主键，要求非空且唯一，应该在插入之前先给生成一个唯一值。
			labs.setId(UUID.randomUUID().toString());
			labsMapper.insert(labs);
		}else {
			labsMapper.updateByPrimaryKey(labs);
		}
	}

	@Transactional
	@Override
	public Map<String, List<String>> removeByBatch(List<String> ids) {
		/**
		 *  返回结果：
		 *  	success： 在数据库中存在，并且成功删除的
		 *  	error:	 在数据库中不存在的，
		 * 业务：
		 * 	存在在数据库中的实验室，判断其是否有关联的设备，如果解除有关联。
		 */
		if(ObjectUtils.isEmpty(ids)) {
			throw new DataValidateException("请至少输入一个实验室id！");
		}
		List<String> existId = labsExMapper.existByIds(ids);
		if(ObjectUtils.isEmpty(existId)) {
			throw new DataValidateException(
					"编号为:"+ids+"的实验室均不存在！");
		}
		//解绑实验室设备。
		deviceMapper.unbindDeviceByLabsId(existId);
		//删除实验室
		labsExMapper.deleteByBatch(existId);
		//从用户输入的id中移除existId留下所有不存在的
		ids.removeAll(existId);
		Map<String, List<String>> result = 
				new HashMap<>();
		result.put("success", existId);
		result.put("error", ids);
		return result;
	}

	@Override
	public PageInfo<SmartLabs> findByCondition(int pageNum, int pageSize, String key) {
		key = ObjectUtils.isEmpty(key)?null:"%"+key+"%";
		PageHelper.startPage(pageNum, pageSize,true);
		//检索方法，待实现
		List<SmartLabs> list = 
				labsExMapper.selectByCondition(key);
		return new PageInfo<>(list);
	}

}




