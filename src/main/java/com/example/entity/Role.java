package com.example.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JK
 */
@Component(value = "Role")
@Scope("prototype")
public class Role
{
	private int roleid;
	private String rolename;
	private List<Userinfo> userinfos;

	public int getRoleid()
	{
		return roleid;
	}

	public void setRoleid(int roleid)
	{
		this.roleid = roleid;
	}

	public String getRolename()
	{
		return rolename;
	}

	public void setRolename(String rolename)
	{
		this.rolename = rolename;
	}

	@Override
	public String toString()
	{
		return "Role{" + "roleid=" + roleid + ", rolename='" + rolename + '\'' + '}';
	}

	public List<Userinfo> getUserinfos()
	{
		return userinfos;
	}

	public void setUserinfos(List<Userinfo> userinfos)
	{
		this.userinfos = userinfos;
	}
}
