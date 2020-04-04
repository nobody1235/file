package com.example.mapper;


import com.example.entity.TreeData;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * @author JK
 */
@Mapper
public interface RoleMenuSqlMap
{
	//	查询对应角色的菜单
	public List<TreeData> findRMID(int roleid);

	//查询所有的菜单
	public List<TreeData> findAllID();

	//删除对应的所有的菜单的信息
	public void delMenu(int roleid);

	//添加对应的菜单的信息
	public void insertMenu(Map<String, Object> map);

}
