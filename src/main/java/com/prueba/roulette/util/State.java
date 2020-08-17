package com.prueba.roulette.util;

public enum State {
	OPEN("abierta"),
	CLOSED("cerrada");
	
	private final String realName;
	
	private State(String realName) {
	    this.realName = realName;
	}
	
	public String getRealName() {
	    return realName;
	}
	
}
