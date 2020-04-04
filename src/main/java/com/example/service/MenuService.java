package com.example.service;

import com.example.entity.MenusInfo;
import com.example.mapper.MenuSqlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author JK
 */
@Service("MenuService")
public class MenuService
{
	//	设置菜单的值内容

	@Autowired
	MenuSqlMap menuSqlMap;

	@Transactional
	public List<MenusInfo> findMenuID(int roleid){
		List<MenusInfo> menuList1 = menuSqlMap.findMenuID(roleid);
		return menuList1;
	}
	@Transactional
	public List<MenusInfo> findMenusID(Map<String, Object> map){
		List<MenusInfo> menuList2 = menuSqlMap.findMenusID(map);
		return menuList2;
	}
}
