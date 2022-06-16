package com.vk.demo.objectCreation;

import java.util.TimeZone;

public class Parrent {
	
	{
		System.out.println("1 Parrent  class called");

	}

	public Parrent() {
		System.out.println(" 2 object created for Parrent");
	}

	public void m() {
		System.out.println("I am parrent");
	}
	public void m1() {
		System.out.println("as a Parrent i got accessed from  "+this.getClass().getName());
	}

}
