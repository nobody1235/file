package com.example.service;

import com.example.entity.TreeData;
import com.example.mapper.RoleMenuSqlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
/**
 * @author JK
 */
@Service("RoleMenuService")
public class RoleMenuService
{
	@Autowired
	RoleMenuSqlMap roleMenuSqlMap;
	//查询所有的菜单
	@Transactional
	public List<TreeData> findAllID(){
		List<TreeData> treeDataAll = roleMenuSqlMap.findAllID();
		return  treeDataAll;
	}
	//	查询对应角色的菜单
	@Transactional
	public List<TreeData> findRMID(int roleid){
		List<TreeData> treeData1 = roleMenuSqlMap.findRMID(roleid);
		return  treeData1;
	}
	//删除对应的所有的菜单的信息
	@Transactional
	public void delMenu(int roleid){
		roleMenuSqlMap.delMenu(roleid);
	}
	//添加对应的菜单的信息
	@Transactional
	public void insertMenu(Map<String, Object> map){
		roleMenuSqlMap.insertMenu(map);
	}
}
