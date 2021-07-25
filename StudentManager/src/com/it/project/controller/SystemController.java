package com.it.project.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.it.project.service.StudentService;
import com.it.project.service.TeacherService;
import com.it.project.entity.Student;
import com.it.project.entity.Teacher;
import com.it.project.entity.User;
import com.it.project.service.UserService;
import com.it.project.util.CpachaUtil;
import org.apache.commons.lang.StringUtils;


/**
 * 
 * 系统主页控制器
 * @author quhonglei
 *
 */
//下面这两个注解一定要有
@RequestMapping ("/system")
@Controller
public class SystemController {
	
		//引入变量
		@Autowired
		private UserService userService;
		@Autowired
		private StudentService studentService;
		@Autowired
		private TeacherService teacherService;
	
		@RequestMapping(value = "/index",method = RequestMethod.GET)
		public ModelAndView index(ModelAndView model){
			model.setViewName("system/index");
			return model;
		}
		/**
		 * 登录页面
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/login",method = RequestMethod.GET)
		public ModelAndView login(ModelAndView model){
			model.setViewName("system/login");
			return model;
		}
		
		/**
		 * 注销登录 
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/login_out",method=RequestMethod.GET)
		public String loginOut(HttpServletRequest request){
			request.getSession().setAttribute("user", null);
			return "redirect:login";
		}
		
		/**
		 * 登录表单提交
		 * @param model
		 * @return
		 */
		
		@RequestMapping(value = "/login",method = RequestMethod.POST)
		@ResponseBody
		public Map<String, String> login(
				@RequestParam(value="username",required=true) String username,
				@RequestParam(value="password",required=true) String password,
				@RequestParam(value="vcode",required=true) String vcode,
				@RequestParam(value="type",required=true) int type,
				HttpServletRequest request
				){
			Map<String, String> ret = new HashMap<String, String>();
			if(StringUtils.isEmpty(username)) {
				ret.put("type","error");
				ret.put("msg","用户名不能为空");
				return ret;
			}
			if(StringUtils.isEmpty(password)) {
				ret.put("type","error");
				ret.put("msg","密码不能为空");
				return ret;
			}
			if(StringUtils.isEmpty(vcode)) {
				ret.put("type","error");
				ret.put("msg","验证码不能为空");
				return ret;
			}
			String loginCpacha = (String)request.getSession().getAttribute("loginCpacha");
			if(StringUtils.isEmpty(loginCpacha)) {
				ret.put("type","error");
				ret.put("msg","长时间未操作，会话已失效，请刷新后重试！");
				return ret;
			}
			if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())) {
				ret.put("type","error");
				ret.put("msg","验证码错误！");
				return ret;
			}
			//清空会话里面的验证码，节省内存
			request.getSession().setAttribute("loginCpacha", null);
			//从数据库中查找用户
			if(type == 1) {
				//管理员登录
				User user = userService.findByUserName(username);
				if(user == null) {
					ret.put("type", "error");
					ret.put("msg", "不存在该用户!");
					return ret;
				}
				if(!password.equals(user.getPassword())) {
					ret.put("type", "error");
					ret.put("msg", "密码错误!");
					return ret;
				}
				request.getSession().setAttribute("user", user);
			}
			if(type == 2) {
				//学生登录
				Student student = studentService.findByUserName(username);
				if(student == null){
					ret.put("type", "error");
					ret.put("msg", "不存在该学生!");
					return ret;
				}
				if(!password.equals(student.getPassword())){
					ret.put("type", "error");
					ret.put("msg", "密码错误!");
					return ret;
				}
				request.getSession().setAttribute("user", student);
			}
			if(type == 3) {
				//教师学生登录
				Teacher teacher = teacherService.findByUserName(username);
				if(teacher == null){
					ret.put("type", "error");
					ret.put("msg", "不存在该教师!");
					return ret;
				}
				if(!password.equals(teacher.getPassword())){
					ret.put("type", "error");
					ret.put("msg", "密码错误!");
					return ret;
				}
				request.getSession().setAttribute("user", teacher);
			}
			request.getSession().setAttribute("userType", type);
			
			ret.put("type", "success");
			ret.put("msg", "登录成功!");
			return ret;
		}
		
		/**
		 * 生成验证码
		 * @param request
		 * @param vl
		 * @param w
		 * @param h
		 * @param response
		 */
		@RequestMapping(value = "/get_cpacha",method = RequestMethod.GET)
		public void getCpacha(HttpServletRequest request,
				@RequestParam (value = "vl",defaultValue = "4",required = false) Integer vl,
				@RequestParam (value = "w",defaultValue = "98",required = false) Integer w,
				@RequestParam (value = "h",defaultValue = "33",required = false) Integer h,
				HttpServletResponse response) {
			//生成验证码对象
		CpachaUtil cpachaUtil = new CpachaUtil(vl,w,h);
		String generatorVCode = cpachaUtil.generatorVCode();
		//创建一个session会话
		request.getSession().setAttribute("loginCpacha", generatorVCode);
		//生成一个图像，true表示有干扰线
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		//将图片写入
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		}
}
