package com.cj.mywidget;

import java.util.Properties;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ItemInfo extends Properties{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6605699808074178416L;

	@Override
	public Object setProperty(String name, String value) {
		// 
		return super.setProperty(name, value == null ? "" : value);
	}
	
	@Override
	public String getProperty(String name) {
		String value = super.getProperty(name);
		return value == null ? "" : value;
	}
	
	public void parse(Node mInfoNode) 
	{
		if(!mInfoNode.hasChildNodes())return ;
		NodeList nodeList=mInfoNode.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++)
		{
			Node node =nodeList.item(i);
			this.setProperty(node.getNodeName(), node.getTextContent());
		}
	}
}
