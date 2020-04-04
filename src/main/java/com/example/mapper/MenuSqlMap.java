package com.example.mapper;


import com.example.entity.MenusInfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * @author JK
 * 菜单方面的方法使用
 */
@Mapper
public interface MenuSqlMap
{
	//	设置菜单的值内容
	public List<MenusInfo> findMenuID(int roleid);
	public List<MenusInfo> findMenusID(Map<String, Object> map);

}
