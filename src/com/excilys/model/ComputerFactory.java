package com.excilys.model;

public class ComputerFactory {

	public ComputerFactory() {
		super();
	}
	
	public Computer getComputer() {
		Computer comp = new Computer();
		return comp;
	}
	
}
