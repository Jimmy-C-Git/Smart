package com.cj.smart;

import java.util.ArrayList;
import java.util.Properties;

import org.w3c.dom.Node;

public class UserInfo extends Properties{
	private static final long serialVersionUID = 526486857746589638L;
	private boolean isLogedin=false;//是否已经登录
	private ArrayList<String> roleList=new ArrayList<String>();
	@Override
	public String getProperty(String name) {
		String value = super.getProperty(name);
		return value == null ? "" : value;
	}
	
	@Override
	public Object setProperty(String name, String value) {
		return super.setProperty(name, value==null? "" : value );
	}
	/*<UserID>efae0a1ce5de457897943f3ec73c7ec4</UserID>
    <LoginID>Admin</LoginID>
    <LoginLogID>592ce14ff80744a3bb05c57d6f2601ff</LoginLogID>
    <UserName>系统管理员</UserName>
    <Group/>
    <Email/>
    <MobilePIN/>
    <Comment/>
    <role>Administrator</role>
    <role>MOBILE</role>*/
	public boolean isLogedin() {
		return isLogedin;
	}

	public void setLogedin(boolean isLogedin) {
		this.isLogedin = isLogedin;
	}

	public void parseDataNode(Node dataNode)
	{
		if(dataNode.hasChildNodes())
		{
			roleList.clear();
			for(int i=0;i<dataNode.getChildNodes().getLength();i++)
			{
				Node node =dataNode.getChildNodes().item(i);
				if(!node.getNodeName().equals("role"))
					this.setProperty(node.getNodeName(), node.getTextContent());
				else 
					roleList.add(node.getTextContent());
			}
		}
		
	}
	public boolean getRole(String role)
	{
		for(int i=0;i<roleList.size();i++)
		{
			if(roleList.get(i).equals(role))
				return true;
		}
		return false;
	}
}
