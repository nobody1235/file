package com.example.mapper;

import com.example.entity.LogInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogInfoSqlMap
{
	//	注册--增加日志的记录信息
	public int addLog(LogInfo logInfo);
}
