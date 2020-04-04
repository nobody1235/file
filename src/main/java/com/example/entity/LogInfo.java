package com.example.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "LogInfo")
@Scope("prototype")
public class LogInfo
{
	//操作id
	private int logid;
	//操作人
	private String operator;
	//操作内容
	private String description;
	//操作类型
	private String logtype;
	//请求IP
	private String requestip;
	//异常编码
	private String exceptioncode;
	//异常详细信息
	private String exceptiondetail;
	//创建内容
	private String createby;
	//创建时间
	private String createdate;

	public int getLogid()
	{
		return logid;
	}

	public void setLogid(int logid)
	{
		this.logid = logid;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getLogtype()
	{
		return logtype;
	}

	public void setLogtype(String logtype)
	{
		this.logtype = logtype;
	}

	public String getRequestip()
	{
		return requestip;
	}

	public void setRequestip(String requestip)
	{
		this.requestip = requestip;
	}

	public String getExceptioncode()
	{
		return exceptioncode;
	}

	public void setExceptioncode(String exceptioncode)
	{
		this.exceptioncode = exceptioncode;
	}

	public String getExceptiondetail()
	{
		return exceptiondetail;
	}

	public void setExceptiondetail(String exceptiondetail)
	{
		this.exceptiondetail = exceptiondetail;
	}

	public String getCreateby()
	{
		return createby;
	}

	public void setCreateby(String createby)
	{
		this.createby = createby;
	}

	public String getCreatedate()
	{
		return createdate;
	}

	public void setCreatedate(String createdate)
	{
		this.createdate = createdate;
	}
}
