package com.example.service;

import com.example.entity.Userinfo;
import com.example.mapper.DocumentSqlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author JK
 */
@Service("DocumentService")
public class DocumentService
{
	@Autowired
	DocumentSqlMap documentSqlMap;
	//查看所有的文档信息
	public List<Userinfo> findDocument(Map<String, Object> map){
		List<Userinfo> users = documentSqlMap.findDocument(map);
		return users;
	}
	//添加文档信息
	@Transactional
	public int addDocument(Map<String, Object> map){
		int result = documentSqlMap.addDocument(map);
		return result;
	}

	//更新对应的审核条件--审核通过
	public int updateapproved(String uploadtime){
		int result = documentSqlMap.updateapproved(uploadtime);
		return result;
	}

	//更新对应的审核条件--审核不通过
	public int updatenoapproved(String uploadtime){
		int result = documentSqlMap.updatenoapproved(uploadtime);
		return result;
	}
}
