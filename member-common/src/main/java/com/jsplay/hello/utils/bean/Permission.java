package com.jsplay.hello.utils.bean;

import java.util.ArrayList;
import java.util.List;

public class Permission {

	private Integer id;
	private Integer pid;
	private String icon;
	private String url;
	private String name;
	private boolean open = true;
	private boolean checked = false;
	private List<Permission> children = new ArrayList<Permission>();
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public List<Permission> getChildren() {
		return children;
	}
	public void setChildren(List<Permission> children) {
		this.children = children;
	}
	
}
