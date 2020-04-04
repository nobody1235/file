package com.example.service;

import com.example.entity.Userinfo;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author JK
 */
@Service("UserService")
public class UserService
{
	@Autowired
	private UserMapper userMapper;

	//	添加用户信息
	@Transactional

	public int addUser(Map<String, Object> map)
	{
		int result = userMapper.addUser(map);
		return result;
	}

	//  登录语句
	//	@Transactional
	public Userinfo findUser(Userinfo userinfo)
	{
		Userinfo userinfo1 = userMapper.findUser(userinfo);
		return userinfo1;
	}

	//	查询分页的内容
	public List<Userinfo> findAll(Map<String, Object> map)
	{
		List<Userinfo> users = userMapper.findAll(map);
		return users;
	}

	//	删除语句
	@Transactional
	public int deleteById(int userid)
	{
		int result = userMapper.deleteById(userid);
		return result;
	}

	//	更新语句信息
	@Transactional
	public int updateById(Userinfo users)
	{
		int result = userMapper.updateById(users);
		return result;
	}
	//设置对应的用户名的重复的查询
	public Userinfo selectFrontName(String username)
	{
		Userinfo userinfo = userMapper.selectFrontName(username);
		return userinfo;
	}

	//对于状态值的判断是不是启用还是禁用
	public int updateState(Map<String,Object> map)
	{
		int result = userMapper.updateState(map);
		return result;
	}


	/*后台的用户登录*/
	public Userinfo findLogin(Userinfo userinfo){
		Userinfo user= userMapper.selectAdmin(userinfo);
		return user;
	}
}
