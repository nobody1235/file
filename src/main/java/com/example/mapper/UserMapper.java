package com.example.mapper;

import com.example.entity.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * @author JK
 */
@Mapper
public interface UserMapper
{
/*
* 前台信息*/
	//  登录语句
	public Userinfo findUser(Userinfo userinfo);

	//	查询分页的内容
	public List<Userinfo> findAll(Map<String, Object> map);

	//	删除语句
	public int deleteById(int userid);

	//	更新语句信息
	public int updateById(Userinfo userinfo);

	//	注册--增加用户信息
	public int addUser(Map<String, Object> map);

	//设置对应的用户名的重复的查询
	public Userinfo selectFrontName(String username);

	//对于状态值的判断是不是启用还是禁用
	public int updateState(Map<String,Object> map);


	/*
	* 后台信息*/

	//	后台的登录
	public Userinfo selectAdmin(Userinfo userinfo);


}
