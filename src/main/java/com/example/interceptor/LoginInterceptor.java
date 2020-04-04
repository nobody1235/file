package com.example.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JK
 */
public class LoginInterceptor implements HandlerInterceptor
{
	/*该方法将在请求处理之前进行调用，只有该方法返回true，才会继续执行后续的Interceptor
	和Controller，当返回值为true 时就会继续调用下一个Interceptor的preHandle 方法，
	如果已经是最后一个Interceptor的时候就会是调用当前请求的Controller方法；*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception
	{
		//获取请求URL的路径
		String uri = request.getRequestURI();
		//判断路径是登录验证，还是要进去对应的登录,controller对应的里面进行执行
		//前台和后台的添加
		if (uri.endsWith("/servlet/front/login")||uri.endsWith("/servlet/front/reginster") || uri.endsWith("/servlet/back/admin_login"))
		{
			return true;
		}
		//		其他的情况判断session里面是否有key，有的话，继续用户操作
		//		前台
		if (request.getSession() != null && request.getSession().getAttribute("loginUser") != null
		&& uri.indexOf("front") != -1)
		{
			System.out.println("loginUser");
			return true;
		}
		//后台
		if (request.getSession() != null && request.getSession().getAttribute("loginAdmin") != null
		&& uri.indexOf("back") != -1)
		{
			System.out.println("loginAdmin");
			return true;
		}
		//最后判断是前台界面还是后台界面----进行，如果是前台，前面的方法都没有执行的话，就会进去这个方法跳转到对应的默认页面。
		if (uri.indexOf("front") != -1)
		{
			System.out.println("前台登录");
			response.sendRedirect(request.getContextPath() + "/servlet/pfront/login");
			return false;
		} else if (uri.indexOf("back") != -1)
		{
			System.out.println("后台登录");
			response.sendRedirect(request.getContextPath() + "/servlet/pback/admin_login");
			return false;
		}else{
			System.out.println("拦截器---异常情况");
			return true;
		}
	}

	/*该方法将在请求处理之后，DispatcherServlet进行视图返回渲染之前进行调用，
	可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作。*/
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse Response, Object o, ModelAndView modelAndView) throws Exception
	{

	}

	/*该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行，
	该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。
	用于进行资源清理。*/
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse Response, Object o, Exception e) throws Exception
	{

	}
}
