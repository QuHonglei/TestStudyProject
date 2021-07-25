package com.it.project.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.it.project.service.GradeService;
import com.it.project.entity.Grade;
import com.it.project.entity.Clazz;
import com.it.project.page.Page;
import com.it.project.service.ClazzService;
import com.it.project.util.StringUtil;

import net.sf.json.JSONArray;

@RequestMapping("/clazz")
@Controller
public class ClazzController {
	
	@Autowired
	private GradeService gradeService;
	@Autowired
	private ClazzService clazzService;
	/**
	 * 获取专业班级页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("clazz/clazz_list");
		List<Grade> findAll = gradeService.findAll();
		model.addObject("gradeList",findAll );
		model.addObject("gradeListJson",JSONArray.fromObject(findAll));
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
			@RequestParam(value="gradeId",required=false) Long gradeId,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(gradeId != null){
			queryMap.put("gradeId", gradeId);
		}
		queryMap.put("name", "%"+name+"%");//like查询要加%%，否则不能识别
		queryMap.put("offset",page.getOffset());//limit分页从0页开始
		queryMap.put("pageSize", page.getRows());//每页10条记录
		ret.put("rows", clazzService.findList(queryMap));
		ret.put("total", clazzService.getTotal(queryMap));//默认显示10页
		return ret;
	}
	
	/**
	 * 添加专业班级
	 * @param Clazz
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Clazz clazz){
		Map<String, String> ret = new HashMap<String, String>();
		if(clazz == null) {
			ret.put("type", "error");
			ret.put("msg", "数据源绑定出错，请联系开发者！");
			return ret;
		}
		if(StringUtils.isEmpty(clazz.getName())) {
			ret.put("type", "error");
			ret.put("msg", "专业班级名称不能为空");
			return ret;
		}
		if(clazz.getGradeId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属年级！");
			return ret;
		}
		Clazz existClazz = clazzService.findByClazzName(clazz.getName());
		if(existClazz != null) {
			ret.put("type", "error");
			ret.put("msg", "该专业班级名已存在！");
			return ret;
		}
		if(clazzService.add(clazz) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		return ret;
	}
	
	/**
	 * 编辑修改专业班级
	 * @param Clazz
	 * @return
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Clazz Clazz){
		Map<String, String> ret = new HashMap<String, String>();
		if(Clazz == null) {
			ret.put("type", "error");
			ret.put("msg", "数据源绑定出错，请联系开发作者！");
		}
		if(StringUtils.isEmpty(Clazz.getName())) {
			ret.put("type", "error");
			ret.put("msg", "专业班级名称不能为空");
			return ret;
		}
		Clazz existClazz = clazzService.findByClazzName(Clazz.getName());
		if(existClazz != null) {
			if(Clazz.getId() != existClazz.getId()){
				ret.put("type", "error");
				ret.put("msg", "该专业班级已经存在!");
				return ret;
			}
		}
		if(clazzService.edit(Clazz) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		return ret;
	}
	/**
	 * 批量删除专业班级
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
			if(clazzService.delete(StringUtil.joinString(Arrays.asList(ids), ",")) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败！");
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			ret.put("type", "error");
			ret.put("msg", "该专业班级下存在学生信息，请勿冲动！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "专业班级删除成功！");
		return ret;
	}

}
