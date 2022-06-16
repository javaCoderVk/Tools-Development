package com.vk.demo.objectCreation;

import java.util.List;

public class Employee {

    private String name;
    private POSITION position;
    private List<String> skilltree;	
    private Address address;

   public Employee() {
	super();
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public POSITION getPosition() {
	return position;
}

public void setPosition(POSITION position) {
	this.position = position;
}

public List<String> getSkilltree() {
	return skilltree;
}

public void setSkilltree(List<String> skilltree) {
	this.skilltree = skilltree;
}

public Address getAddress() {
	return address;
}

public void setAddress(Address address) {
	this.address = address;
}

@Override
public String toString() {
	return "Employee [name=" + name + ", position=" + position + ", skilltree=" + skilltree + ", address=" + address
			+ "]";
}

   
}
