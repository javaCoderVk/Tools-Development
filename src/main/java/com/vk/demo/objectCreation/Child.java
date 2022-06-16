package com.vk.demo.objectCreation;

public class Child extends Parrent {
	{
		System.out.println("1 child  class called");
	}

	public Child() {
		System.out.println(" 2 object created for child");

	}

	public void m() {
		System.out.println("I am child");
	}

	public void m2() {
		
		System.out.println(" as a child i got accessed from "+this.getClass().getName());
	}

}
