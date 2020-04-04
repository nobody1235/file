//package com.example.entity;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
///**
// * @author JK
// */
//@Component(value = "Admin")
//@Scope("prototype")
//public class Admin
//{
//	private int adminid;
//	private String adaccount;
//	private String adpassword;
//	private String adname;
//	private int adsal;
//	private String job;
//	private int roleid;
//	private Role role;
//
//	public Admin()
//	{
//	}
//
//	public Admin(String adaccount, String adpassword)
//	{
//		this.adaccount = adaccount;
//		this.adpassword = adpassword;
//	}
//
//	public int getAdminid()
//	{
//		return adminid;
//	}
//
//	public void setAdminid(int adminid)
//	{
//		this.adminid = adminid;
//	}
//
//	public String getAdaccount()
//	{
//		return adaccount;
//	}
//
//	public void setAdaccount(String adaccount)
//	{
//		this.adaccount = adaccount;
//	}
//
//	public String getAdpassword()
//	{
//		return adpassword;
//	}
//
//	public void setAdpassword(String adpassword)
//	{
//		this.adpassword = adpassword;
//	}
//
//	public String getAdname()
//	{
//		return adname;
//	}
//
//	public void setAdname(String adname)
//	{
//		this.adname = adname;
//	}
//
//	public int getAdsal()
//	{
//		return adsal;
//	}
//
//	public void setAdsal(int adsal)
//	{
//		this.adsal = adsal;
//	}
//
//	public String getJob()
//	{
//		return job;
//	}
//
//	public void setJob(String job)
//	{
//		this.job = job;
//	}
//
//	public int getRoleid()
//	{
//		return roleid;
//	}
//	public void setRoleid(int roleid)
//	{
//		this.roleid = roleid;
//	}
//	public Role getRole()
//	{
//		return role;
//	}
//
//	public void setRole(Role role)
//	{
//		this.role = role;
//	}
//
//	@Override
//	public String toString()
//	{
//		return "Admin{" + "adminid=" + adminid + ", adaccount='" + adaccount + '\'' + ", adpassword='" + adpassword + '\'' + ", adname='" + adname + '\'' + ", adsal=" + adsal + ", job='" + job + '\'' + ", roleid='" + roleid + '\'' + ", role=" + role + '}';
//	}
//}
