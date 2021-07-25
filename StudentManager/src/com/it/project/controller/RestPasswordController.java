package com.it.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.it.project.entity.Student;
import com.it.project.entity.User;
import com.it.project.entity.Teacher;
import com.it.project.service.StudentService;
import com.it.project.service.TeacherService;
import com.it.project.service.UserService;

/**
 * 密码管理
 * @author llq
 *
 */
@RequestMapping("/system")
@Controller
public class RestPasswordController {
	
	@Autowired
	public UserService userService;
	@Autowired
	public StudentService studentService;
	@Autowired
	public TeacherService teacherService;
	
	/**
	 * 获取密码页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("system/restpassword");
		return model;
	}
	/**
	 * 修改当前用户密码
	 * @param clazz
	 * @return
	 */

	
	@RequestMapping(value = "/editpassword",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> editpassword(User user,Student student,Teacher teacher,
			HttpServletRequest request){
		Map<String, String> ret = new HashMap<String,String>();		
		if(user == null) {
			ret.put("type", "error");
			ret.put("msg", "数据源绑定出错，请联系开发作者！");
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "密码不能为空！");
			return ret;
		}
		//从session对话中，获取当前登录用户类型，并获取当前用户id
		Object attribute = request.getSession().getAttribute("userType");
		if("1".equals(attribute.toString())) {//如果是“1”，说明是管理员，只显示管理员自己的信息，获取当前用户id
			//将当前用户类型转换为User类型
			User loginedUser = (User)request.getSession().getAttribute("user");
			user.setId(loginedUser.getId());//将当前登录用户id，传入后台
			//判断新旧密码是否相同
			User existUser = userService.findByUserId(user.getId());//根据id获取当前登录用户对象
			if(existUser != null) {
				System.out.println(user.getPassword());//打印前端传入的password
				System.out.println(existUser.getPassword());//打印当前登录用户未修改前的密码
				//判断新旧密码是否相同
				if(user.getPassword() != existUser.getPassword()) {
				ret.put("type", "error");
				ret.put("msg", "新旧密码相同，请重新输入！");
				return ret;
				}
			}
			//判断是否获取到前端传入的对象
			if(userService.editpassword(user) <= 0) {
				ret.put("type", "error");
				ret.put("msg", "修改失败！");
				return ret;
			}
		}
		if("2".equals(attribute.toString())) {//如果是“2”，说明是学生，只显示学生自己的信息，获取当前用户id
			//将当前用户类型转换为Student类型
			Student loginedStudent = (Student)request.getSession().getAttribute("user");
			student.setId(loginedStudent.getId());//将当前登录用户id，传入后台
			//判断新旧密码是否相同
			User existStudent = userService.findByUserId(student.getId());//根据id获取当前登录用户对象
			if(existStudent != null) {
				System.out.println(student.getPassword());//打印前端传入的password
				System.out.println(existStudent.getPassword());//打印当前登录用户未修改前的密码
				//判断新旧密码是否相同
				if(student.getPassword() != existStudent.getPassword()) {
				ret.put("type", "error");
				ret.put("msg", "新旧密码相同，请重新输入！");
				return ret;
				}
			}
			//判断是否获取到前端传入的对象
			if(studentService.editpassword(student) <= 0) {
				ret.put("type", "error");
				ret.put("msg", "修改失败！");
				return ret;
			}		
		}
		if("3".equals(attribute.toString())) {//如果是“3”，说明是教师，只显示教师自己的信息，获取当前用户id
			//将当前用户类型转换为Student类型
			Teacher loginedTeachaer = (Teacher)request.getSession().getAttribute("user");
			teacher.setId(loginedTeachaer.getId());//将当前登录用户id，传入后台
			//判断新旧密码是否相同
			User existTeachaer = userService.findByUserId(teacher.getId());//根据id获取当前登录用户对象
			if(existTeachaer != null) {
				System.out.println(teacher.getPassword());//打印前端传入的password
				System.out.println(existTeachaer.getPassword());//打印当前登录用户未修改前的密码
				//判断新旧密码是否相同
				if(teacher.getPassword() != existTeachaer.getPassword()) {
				ret.put("type", "error");
				ret.put("msg", "新旧密码相同，请重新输入！");
				return ret;
				}
			}
			//判断是否获取到前端传入的对象
			if(teacherService.editpassword(teacher) <= 0) {
				ret.put("type", "error");
				ret.put("msg", "修改失败！");
				return ret;
			}		
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		return ret;
	}

}

