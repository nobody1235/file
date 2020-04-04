package com.example.controller;//package com.great.controller;
//
//import com.great.annotation.MyController;
//import com.great.annotation.MyMapper;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author JK
// */
//@MyController
//@MyMapper("/test")
//public class TestController
//{
//	@MyMapper("/doTest")
//	public void test1(HttpServletRequest request, HttpServletResponse response,String param){
//		System.out.println(param);
//		outWithHtml(response,param);
//	}
//	private void outWithHtml(HttpServletResponse response,String html)
//	{ try
//		{//将请求、响应的编码均设置为UTF-8（防止中文乱码）
//			response.setCharacterEncoding("UTF-8");
//			response.setContentType("application/json;charset=utf-8");
//			//只能打印输出文本格式的（包括html标签）--打印输出流
//			response.getWriter().write(html);
//			//释放资源
//			response.getWriter().flush();
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}finally
//		{
//			try
//			{
//				response.getWriter().close();
//			} catch (IOException e)
//			{
//				e.printStackTrace();
//			}
//		}
//
//	}
//}
