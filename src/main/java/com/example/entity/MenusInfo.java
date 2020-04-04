package com.example.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author JK
 * 菜单表内容
 */
@Component(value = "MenusInfo")
@Scope("prototype")
public class MenusInfo
{
	private int menuid;
	private String menuname;
	private String menuurl;
	private int menuparentid;

	public int getMenuid()
	{
		return menuid;
	}

	public void setMenuid(int menuid)
	{
		this.menuid = menuid;
	}

	public String getMenuname()
	{
		return menuname;
	}

	public void setMenuname(String menuname)
	{
		this.menuname = menuname;
	}

	public String getMenuurl()
	{
		return menuurl;
	}

	public void setMenuurl(String menuurl)
	{
		this.menuurl = menuurl;
	}

	public int getMenuparentid()
	{
		return menuparentid;
	}

	public void setMenuparentid(int menuparentid)
	{
		this.menuparentid = menuparentid;
	}

	public MenusInfo(int menuid, String menuname, String menuurl, int menuparentid)
	{
		this.menuid = menuid;
		this.menuname = menuname;
		this.menuurl = menuurl;
		this.menuparentid = menuparentid;
	}
	public MenusInfo()
	{
	}
}
