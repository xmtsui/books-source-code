package com.example;

public class Sample {
	private Sample instance;
	
	public void setSample(Object instance) {
		this.instance = (Sample) instance;
	}
	
	public boolean testInstanceOf(Object instance){
		return instance instanceof Sample;
	}
}
