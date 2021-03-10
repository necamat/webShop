package com.mycompany.assigmentweb.model;


 
public enum State {

	ACTIVE("Active"),
	INACTIVE("Inactive");
	
	
	private String state;
	
	private State(final String state){
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

