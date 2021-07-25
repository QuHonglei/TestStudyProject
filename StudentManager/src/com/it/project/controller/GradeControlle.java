package com.it.project.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.it.project.entity.Grade;
import com.it.project.page.Page;
import com.it.project.service.GradeService;
import com.it.project.util.StringUtil;

@RequestMapping("/grade")
@Controller

public class GradeControlle {
	
	@Autowired
	public GradeService gradeService;
	
	/**
	 * 获取年级页面（链接grade_list.jsp）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView grade(ModelAndView model) {
		model.setViewName("grade/grade_list");
		return model;
	}
	
	/**
	 * 分页显示列表记录
	 * @return
	 */
	@RequestMapping(value = "/get_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findList(
			@RequestParam(value = "name",required = false,defaultValue = "") String name,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", "%"+name+"%");//like查询要加%%，否则不能识别
		queryMap.put("offset",page.getOffset());//limit分页从0页开始
		queryMap.put("pageSize", page.getRows());//每页10条记录
		ret.put("rows", gradeService.findList(queryMap));
		ret.put("total", gradeService.getTotal(queryMap));//默认显示10页
		return ret;
	}
	
	/**
	 * 添加年级
	 * @param grade
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Grade grade){
		Map<String, String> ret = new HashMap<String, String>();
		if(grade == null) {
			ret.put("type", "error");
			ret.put("msg", "数据源绑定出错，请联系开发者！");
			return ret;
		}
		if(StringUtils.isEmpty(grade.getName())) {
			ret.put("type", "error");
			ret.put("msg", "年级名称不能为空");
			return ret;
		}
		Grade existGrade = gradeService.findByGradeName(grade.getName());
		if(existGrade != null) {
			ret.put("type", "error");
			ret.put("msg", "该年级名已存在！");
			return ret;
		}
		if(gradeService.add(grade) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		return ret;
	}
	
	/**
	 * 编辑修改年级
	 * @param grade
	 * @return
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Grade grade){
		Map<String, String> ret = new HashMap<String, String>();
		if(grade == null) {
			ret.put("type", "error");
			ret.put("msg", "数据源绑定出错，请联系开发作者！");
		}
		if(StringUtils.isEmpty(grade.getName())) {
			ret.put("type", "error");
			ret.put("msg", "年级名称不能为空");
			return ret;
		}
		Grade existGrade = gradeService.findByGradeName(grade.getName());
		if(existGrade != null) {
			if(grade.getId() != existGrade.getId()){
				ret.put("type", "error");
				ret.put("msg", "该年级已经存在!");
				return ret;
			}
		}
		if(gradeService.edit(grade) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		return ret;
	}
	/**
	 * 批量删除年级
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(
			@RequestParam(value="ids[]",required=true) Long[] ids
			){
		Map<String, String> ret = new HashMap<String, String>();
		if(ids == null || ids.length == 0){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的数据！");
			return ret;
		}
		String idsString = "";
		for(Long id:ids){
			idsString += id + ",";
		}
		idsString = idsString.substring(0,idsString.length()-1);
		try {
			if(gradeService.delete(StringUtil.joinString(Arrays.asList(ids), ",")) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败！");
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			ret.put("type", "error");
			ret.put("msg", "该年级下存在班级信息，请勿冲动！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "年级删除成功！");
		return ret;
	}
}
