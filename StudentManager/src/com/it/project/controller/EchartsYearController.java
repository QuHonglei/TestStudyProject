package com.it.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.it.project.entity.Student;
import com.it.project.service.StudentService;

@RequestMapping("/echarts")
@Controller
public class EchartsYearController {
	
	@Autowired
	private StudentService studentService;
	/*
	 * 图表页面
	 */
	@RequestMapping(value = "/year_list")
	public ModelAndView list(ModelAndView model) {
		model.setViewName("echarts/echartsYear_list");
		return model;
	}
	
	@RequestMapping(value = "/countYearStudent", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody List<Student> countYearStudent() {
		List<Student> countYearStudent = studentService.countYearStudent();
		return countYearStudent;
	}
}
