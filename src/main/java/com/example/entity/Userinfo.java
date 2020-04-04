package com.example.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author JK
 */
@Component(value = "Userinfo")
@Scope("prototype")
public class Userinfo
{
	private int userid;
	private String username;
	private String password;
	private String usersex;
	private String schoolmate;
	private String position;
	private String cellphone;
	private String usermail;
	private String addtime;
	private int  addscore;
	private int uploadsum;
	private int downloadsum;
	private String userstate;
	private int roleid;

	public int getUserid()
	{
		return userid;
	}

	public void setUserid(int userid)
	{
		this.userid = userid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUsersex()
	{
		return usersex;
	}

	public void setUsersex(String usersex)
	{
		this.usersex = usersex;
	}

	public String getSchoolmate()
	{
		return schoolmate;
	}

	public void setSchoolmate(String schoolmate)
	{
		this.schoolmate = schoolmate;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	public String getCellphone()
	{
		return cellphone;
	}

	public void setCellphone(String cellphone)
	{
		this.cellphone = cellphone;
	}

	public String getUsermail()
	{
		return usermail;
	}

	public void setUsermail(String usermail)
	{
		this.usermail = usermail;
	}

	public String getAddtime()
	{
		return addtime;
	}

	public void setAddtime(String addtime)
	{
		this.addtime = addtime;
	}

	public int getAddscore()
	{
		return addscore;
	}

	public void setAddscore(int addscore)
	{
		this.addscore = addscore;
	}

	public int getUploadsum()
	{
		return uploadsum;
	}

	public void setUploadsum(int uploadsum)
	{
		this.uploadsum = uploadsum;
	}

	public int getDownloadsum()
	{
		return downloadsum;
	}

	public void setDownloadsum(int downloadsum)
	{
		this.downloadsum = downloadsum;
	}

	public String getUserstate()
	{
		return userstate;
	}

	public void setUserstate(String userstate)
	{
		this.userstate = userstate;
	}

	public int getRoleid()
	{
		return roleid;
	}

	public void setRoleid(int roleid)
	{
		this.roleid = roleid;
	}

	@Override
	public String toString()
	{
		return "Userinfo{" + "userid=" + userid + ", username='" + username + '\'' + ", password='" + password + '\'' + ", usersex='" + usersex + '\'' + ", schoolmate='" + schoolmate + '\'' + ", position='" + position + '\'' + ", cellphone='" + cellphone + '\'' + ", usermail='" + usermail + '\'' + ", addtime='" + addtime + '\'' + ", addscore=" + addscore + ", uploadsum=" + uploadsum + ", downloadsum=" + downloadsum + ", userstate='" + userstate + '\'' + ", roleid=" + roleid + '}';
	}
}
