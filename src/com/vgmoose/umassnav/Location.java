package com.vgmoose.umassnav;

public class Location {

	private String name, addr;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getHref(char dirflag)
	{
		return "http://maps.google.com/maps?saddr=Current+Location&amp;daddr="+this.addr+"&amp;dirflg="+dirflag;
	}
	

}
