package com.example.service;

import com.example.entity.LogInfo;
import com.example.mapper.LogInfoSqlMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("LogService")
public class LogService
{
//	接口
	@Autowired
LogInfoSqlMap logInfoSqlMap;

//	提交内容--添加对应的日志记录
	@Transactional
	public int addLog(LogInfo logInfo)
	{
		int result = logInfoSqlMap.addLog(logInfo);
		return result;
	}
}
