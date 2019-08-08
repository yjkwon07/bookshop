package com.yjkwon.bookshop.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ViewNameInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			String viewName = getViewName(request);
			System.out.println(viewName);
			request.setAttribute("viewName", viewName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private String getViewName(HttpServletRequest request) {
		
		String contextPath =request.getContextPath();
		String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
		if(uri == null || uri.equals("")) {
			uri = request.getRequestURI();
		}
		int begin = 0;
		if(!(contextPath == null || contextPath.equals(""))) {
			begin = contextPath.length();
		}	
		int end = 0;
		if(uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		}
		else if(uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		}
		else {
			end = uri.length();
		}
		String viewName = uri.substring(begin, end);
		if(viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0,viewName.lastIndexOf("."));
		} else if(viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;
	}
}
