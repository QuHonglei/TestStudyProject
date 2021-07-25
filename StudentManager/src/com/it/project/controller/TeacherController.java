package com.it.project.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.it.project.entity.Grade;
import com.it.project.entity.Teacher;
import com.it.project.page.Page;
import com.it.project.service.GradeService;
import com.it.project.service.TeacherService;
import com.it.project.util.StringUtil;

import net.sf.json.JSONArray;

/**
 * 教师信息管理
 * @author llq
 *
 */
@RequestMapping("/teacher")
@Controller
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private GradeService gradeService;
	/**
	 * 教师列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("teacher/teacher_list");
		List<Grade> gradeList = gradeService.findAll();//获取所有院系
		model.addObject("gradeList",gradeList );
		model.addObject("gradeListJson",JSONArray.fromObject(gradeList));//转换为json格式
		return model;
	}
	
	/**
	 * 获取教师列表
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/get_list",method=RequestMethod.POST)
	@ResponseBody//关键字表示可以解析前端传入的json格式的数据
	public Map<String, Object> getList(
			@RequestParam(value="name",required=false,defaultValue="") String name,
			@RequestParam(value="gradeId",required=false) Long gradeId,
			HttpServletRequest request,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", "%"+name+"%");
		Object attribute = request.getSession().getAttribute("userType");
		if("3".equals(attribute.toString())) {
			//说明是老师，只显示老师自己的信息
			Teacher loginedTeacher = (Teacher)request.getSession().getAttribute("user");
			queryMap.put("name","%"+loginedTeacher.getName()+"%");
		}
		if(gradeId != null){
			queryMap.put("gradeId", gradeId);
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", teacherService.findList(queryMap));
		ret.put("total", teacherService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 编辑教师信息
	 * @param clazz
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Teacher teacher){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(teacher.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "教师姓名不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(teacher.getName())){
			ret.put("type", "error");
			ret.put("msg", "教师编号不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(teacher.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "教师登录密码不能为空！");
			return ret;
		}
		if(teacher.getGradeId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属院系！");
			return ret;
		}
		if(isExist(teacher.getName(), teacher.getId())){
			ret.put("type", "error");
			ret.put("msg", "该教师编号已存在！");
			return ret;
		}
		//student.setSn(StringUtil.generateSn("S", ""));
		
		if(teacherService.edit(teacher) <= 0){
			ret.put("type", "error");
			ret.put("msg", "教师修改失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "教师修改成功！");
		return ret;
	}
	
	
	/**
	 * 添加教师信息
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Teacher teacher){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(teacher.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "教师姓名不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(teacher.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "教师登录密码不能为空！");
			return ret;
		}
		if(teacher.getGradeId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属院系！");
			return ret;
		}
		if(isExist(teacher.getName(), null)){
			ret.put("type", "error");
			ret.put("msg", "该教师编号已存在！");
			return ret;
		}
		//自动生成教师编号
		//student.setSn(StringUtil.generateSn("S", ""));
		teacher.setPassword(teacher.getPassword());
		if(teacherService.add(teacher) <= 0){
			ret.put("type", "error");
			ret.put("msg", "教师添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "教师添加成功！");
		return ret;
	}
	
	/**
	 * 删除教师信息
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
		try {
			if(teacherService.delete(StringUtil.joinString(Arrays.asList(ids), ",")) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败！");
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			ret.put("type", "error");
			ret.put("msg", "该教师下存在其他信息，请勿冲动！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "教师删除成功！");
		return ret;
	}
	
	private boolean isExist(String username,Long id){
		Teacher teacher = teacherService.findByUserName(username);
		if(teacher != null){
			if(id == null){
				return true;
			}
			if(teacher.getId().longValue() != id.longValue()){
				return true;
			}
		}
		return false;
	}
}

