package com.prueba.roulette.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BetDATA {

	@JsonProperty("resultado")
	private String result;
	
	@JsonProperty("dinero")
	private Integer amount;
	
	@JsonProperty("apuesta")
	private String betValue;	
		
	public BetDATA() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BetDATA(String betValue, Integer amount, String result) {
		super();
		this.betValue = betValue;
		this.amount = amount;
		this.result = result;
	}

	public String getBetValue() {
		return betValue;
	}
	
	public void setBetValue(String betValue) {
		this.betValue = betValue;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}	
}
