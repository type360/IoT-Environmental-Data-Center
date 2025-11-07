package com.briup.smartlabs.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;

public class EntityUtils {

	//实体对象 转化
	public static <T> T toEntity(Object source,Class<T> target,String...ignore){
		try {
			T result = target.newInstance();
			BeanUtils.copyProperties(source, result, ignore);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//集合对象 转化
	public static <T> Collection<T> toList(Collection<?> source,Class<T> target,String ...ignore){
		try {
			Collection<T> list = new ArrayList<>();
			for(Object obj : source) {
				T result = target.newInstance();
				BeanUtils.copyProperties(obj, result,ignore);
				list.add(result);
				System.out.println("result: "+result);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
