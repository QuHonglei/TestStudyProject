package com.it.project.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.it.project.dao.ClazzDao;

/**
 * 实用工具类
 * @author llq
 *
 */
public class StringUtil {
	@Autowired
	public static ClazzDao clazzDao;

	/**
	 * 将给定的list按照指定的分隔符分割成字符串返回
	 * @param list
	 * @param split
	 * @return
	 */
	public static String joinString(List<Long> list,String split){
		String ret = "";
		for(Long l:list){
			ret += l + split;
		}
		if(!"".equals(ret)){
			ret = ret.substring(0,ret.length() - split.length());
		}
		return ret;
	}
	
	//学号自动生成
//	public static String generateSn(String prefix,String suffix){
//		return prefix + new Date().getTime() + suffix;
//	}
}
