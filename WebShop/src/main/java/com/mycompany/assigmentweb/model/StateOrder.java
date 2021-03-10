package com.mycompany.assigmentweb.model;


 
public enum StateOrder {

	PROCESS("Process"),
	DELIVERED("Delivered"),
	REJECTED("Rejected");
	
	private String state;
	
	private StateOrder(final String state){
		this.state = state;
	}
	
	public String getState(){
		return this.state;
	}

	@Override
	public String toString(){
		return this.state;
	}

	public String getName(){
		return this.name();
	}


}

