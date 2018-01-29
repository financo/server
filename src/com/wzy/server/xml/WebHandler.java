package com.wzy.server.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WebHandler extends DefaultHandler{
	
	private List<Entity> entityList;
	private List<Mapping> mappingList;
	private Entity entity;
	private Mapping mapping;
	private String beginTag;
	private boolean isMap;

	@Override
	public void startDocument() throws SAXException {
		entityList = new ArrayList<Entity>();
		mappingList = new ArrayList<Mapping>();
	}

	@Override
	public void endDocument() throws SAXException {
		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (null != qName) {
			beginTag = qName;
			if (qName.equals("servlet")) {
				isMap = false;
				entity = new Entity();
			}else if (qName.equals("servlet-mapping")) {
				isMap = true;
				mapping = new Mapping();
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (null != beginTag) {
			String str = new String(ch, start, length);
			if (isMap) {
				if (beginTag.equals("servlet-name")) {
					mapping.setName(str);
				}else if (beginTag.equals("url-pattern")) {
					mapping.getUrlPattern().add(str);
				}
			}else {
				if (beginTag.equals("servlet-name")) {
					entity.setName(str);
				}else if (beginTag.equals("servlet-class")) {
					entity.setClazz(str);
				}
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (null != qName) {
			if (qName.equals("servlet")) {
				entityList.add(entity);
			}else if (qName.equals("servlet-mapping")) {
				mappingList.add(mapping);
			}
		}
		beginTag = null;
	}
	
	

	public List<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}

	public List<Mapping> getMappingList() {
		return mappingList;
	}

	public void setMappingList(List<Mapping> mappingList) {
		this.mappingList = mappingList;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Mapping getMapping() {
		return mapping;
	}

	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	public String getBeginTag() {
		return beginTag;
	}

	public void setBeginTag(String beginTag) {
		this.beginTag = beginTag;
	}

	public boolean isMap() {
		return isMap;
	}

	public void setMap(boolean isMap) {
		this.isMap = isMap;
	}
}
