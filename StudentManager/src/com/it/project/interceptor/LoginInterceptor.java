package com.it.project.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

/**
 * 登录过滤拦截器
 * @author quhonglei
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO 自动生成的方法存根

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO 自动生成的方法存根
		String url = request.getRequestURI();
		//System.out.println("进入拦截器，url = " + url);
		Object user = request.getSession().getAttribute("user");
		if (user == null) {
			//表示未登录或者登录状态失效
			System.out.println("未登录或登录失效，url = " + url);
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				//ajax请求
				Map<String, String> ret = new HashMap<String,String>();
				ret.put("type", "error");
				ret.put("msg", "登录状态已失效，请刷新后重新登录！");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			
			response.sendRedirect(request.getContextPath() + "/system/login");
			return false;
		}
		
		return true;
	}

}
