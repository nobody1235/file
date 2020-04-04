package com.example.controller;

import com.example.aoplog.Log;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

	@Log(operationType = "增加操作", operationName = "添加用户")
	public static void testAOP(String userName, String password){
		System.out.println("UserController被调用了~~~~~~~~~~~~~~~~···");

		String tmp = null;
		tmp.substring(1);

	}
	
	@Log(operationType = "修改操作", operationName = "修改用户")
	public void testAOP2(String userName, String password){
		System.out.println("UserController被调用了~~~~~~~~~~~~~~~~···");

		String tmp = null;
		tmp.substring(1);

	}
	
}