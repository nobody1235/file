package com.example.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author JK
 */
@Component(value = "Datatable")
@Scope("prototype")
public class Datatable implements Serializable
{
	private int code;
	private String msg;
	private int count;
	private List<Userinfo> data;

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public List<Userinfo> getData()
	{
		return data;
	}

	public void setData(List<Userinfo> data)
	{
		this.data = data;
	}
}
