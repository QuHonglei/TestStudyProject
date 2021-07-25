package com.it.project.controller;



import java.util.HashMap;
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

import com.it.project.entity.User;
import com.it.project.page.Page;
import com.it.project.service.UserService;



/**
 * 用户管理员控制器
 * @author quhonglei
 *
 */

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	public UserService userService;
	
	/**
	 * 获取用户管理列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("user/user_list");
		return model;
	}
	
	
	/**
	 * 显示用户列表记录
	 * @return
	 */
	
	@RequestMapping(value = "/get_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findList(
			@RequestParam(value="username",required=false,defaultValue="") String username,
			HttpServletRequest request,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("username", "%"+username+"%");//like查询要加%%，否则不能识别
		Object attribute = request.getSession().getAttribute("userType");
		if("1".equals(attribute.toString())) {
			//说明是管理员，只显示管理员自己的信息
			User loginedUser = (User)request.getSession().getAttribute("user");
			queryMap.put("username","%"+loginedUser.getUsername()+"%");
		}
		queryMap.put("offset",page.getOffset());//limit分页从0页开始
		queryMap.put("pageSize", page.getRows());//每页10条记录
		//list.jsp决定将查询到的列表存放到rows里面
		ret.put("rows", userService.findList(queryMap));
		ret.put("total", userService.getTotal(queryMap));//默认显示10页
		return ret;
	
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(User user){
		Map<String, String> ret = new HashMap<String,String>();
		if(user == null) {
			ret.put("type", "error");
			ret.put("msg", "数据源绑定出错，请联系开发作者！");
		}
		if(StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "密码不能为空！");
			return ret;
		}
		User existUser = userService.findByUserName(user.getUsername());
		if(existUser != null) {
			System.out.println(user.getId());
			System.out.println(existUser.getId());
			
			if(user.getId() != existUser.getId()) {
			ret.put("type", "error");
			ret.put("msg", "该用户名已存在！");
			return ret;
			}
		}
		if(userService.edit(user) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		return ret;
	}
	
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(User user){
		Map<String, String> ret = new HashMap<String,String>();
		if(user == null) {
			ret.put("type", "error");
			ret.put("msg", "数据源绑定出错，请联系开发作者！");
		}
		if(StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "密码不能为空！");
			return ret;
		}
		User existUser = userService.findByUserName(user.getUsername());
		if(existUser != null) {
			ret.put("type", "error");
			ret.put("msg", "该用户名已存在！");
			return ret;
		}
		if(userService.add(user) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		return ret;
	}
	
	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete",method =RequestMethod.POST )
	@ResponseBody
	public Map<String, String> delete(
			@RequestParam(value = "ids[]",required = true) Long[] ids
			){
		Map<String, String> ret = new HashMap<String, String>();
		if(ids ==null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的数据");
			return ret;
		}
		String idsString = "";
		for(Long id:ids) {
			idsString += id + ",";
		}
		idsString = idsString.substring(0, idsString.length()-1);
		if(userService.delete(idsString)<=0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
		
	}
	

}
