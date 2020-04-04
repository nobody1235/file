package com.example.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author JK
 * 文档信息类
 */
@Component(value = "Document")
@Scope("prototype")
public class Document
{
	private int documentid;
	private String documentname;
	private String uploadpeople;
	private String uploadtime;
	private int downloadscore;
	private String documenttype;
	private String documentcontent;

	public int getDocumentid()
	{
		return documentid;
	}

	public void setDocumentid(int documentid)
	{
		this.documentid = documentid;
	}

	public String getUploadpeople()
	{
		return uploadpeople;
	}

	public void setUploadpeople(String uploadpeople)
	{
		this.uploadpeople = uploadpeople;
	}

	public String getUploadtime()
	{
		return uploadtime;
	}

	public void setUploadtime(String uploadtime)
	{
		this.uploadtime = uploadtime;
	}

	public int getDownloadscore()
	{
		return downloadscore;
	}

	public void setDownloadscore(int downloadscore)
	{
		this.downloadscore = downloadscore;
	}

	public String getDocumenttype()
	{
		return documenttype;
	}

	public void setDocumenttype(String documenttype)
	{
		this.documenttype = documenttype;
	}

	public String getDocumentname()
	{
		return documentname;
	}

	public void setDocumentname(String documentname)
	{
		this.documentname = documentname;
	}

	public String getDocumentcontent()
	{
		return documentcontent;
	}

	public void setDocumentcontent(String documentcontent)
	{
		this.documentcontent = documentcontent;
	}

	@Override
	public String toString()
	{
		return "Document{" + "documentid=" + documentid + ", documentname='" + documentname + '\'' + ", uploadpeople='" + uploadpeople + '\'' + ", uploadtime='" + uploadtime + '\'' + ", downloadscore=" + downloadscore + ", documenttype='" + documenttype + '\'' + ", documentcontent='" + documentcontent + '\'' + '}';
	}
}
