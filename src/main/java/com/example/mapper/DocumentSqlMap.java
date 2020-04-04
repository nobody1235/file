package com.example.mapper;

import com.example.entity.Userinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author JK
 */
@Mapper
public interface DocumentSqlMap
{
	//查询
	public List<Userinfo> findDocument(Map<String, Object> map);

	//添加
	public int addDocument(Map<String, Object> map);

	//更新对应的审核条件--审核通过
	public int updateapproved(String uploadtime);

	//更新对应的审核条件--审核不通过
	public int updatenoapproved(String uploadtime);
}
