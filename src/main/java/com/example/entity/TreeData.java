package com.example.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JK
 * 树进行显示的根据
 */
@Component(value = "TreeData")
@Scope("prototype")
public class TreeData
{
	//节点标题
	private String title;
	//节点id
	private int id;
	//节点字段名
	private String field;
	//子节点
	private List<TreeData> children;
	//	节点是否初始展开
	private String spread;
	//节点是否初始为选中状态
	private String checked;
	//节点是否为禁用状态
	private String disabled;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
	}

	public List<TreeData> getChildren()
	{
		return children;
	}

	public void setChildren(List<TreeData> children)
	{
		this.children = children;
	}
	public String getSpread()
	{
		return spread;
	}

	public void setSpread(String spread)
	{
		this.spread = spread;
	}

	public String getChecked()
	{
		return checked;
	}

	public void setChecked(String checked)
	{
		this.checked = checked;
	}

	public String getDisabled()
	{
		return disabled;
	}

	public void setDisabled(String disabled)
	{
		this.disabled = disabled;
	}

	@Override
	public String toString()
	{
		return "TreeData{" + "title='" + title + '\'' + ", id='" + id + '\'' + ", field='" + field + '\'' + ", children=" + children + ", spread='" + spread + '\'' + ", checked='" + checked + '\'' + ", disabled='" + disabled + '\'' + '}';
	}
}
