package com.wzy.server.xml;
/**
 * 用户创建从web.xml中解析出的servlet实体
 * @author wzy
 *
 */
public class Entity {
	private String name;
	private String clazz;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
}
