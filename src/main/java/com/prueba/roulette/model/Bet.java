package com.prueba.roulette.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prueba.roulette.util.AppConstants;

public class Bet {

	@JsonProperty("dinero")
	private Integer amount;
	
	@JsonProperty("apuesta")
	private String betValue;
	
	public Bet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bet(String betValue, Integer amount) {
		super();
		this.betValue = betValue;
		this.amount = amount;
	}

	public String getBetValue() {
		return betValue;
	}

	public void setBetValue(String betValue) {
		this.betValue = betValue;
	}
	
	@JsonIgnore
	public Boolean isValid() {
		return (0 < this.amount && this.amount <= 10000 && (this.betValue.equals(AppConstants.BLACK)
				|| this.betValue.equals(AppConstants.RED) || isCorrectRange()));
	}

	Boolean isCorrectRange() {
		try {
			int parse = Integer.parseInt(this.betValue);
			return (0 <= parse && parse <= 36);
		} catch (Exception e) {
			return false;
		}
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Boolean isWinner(String betValue) {
		return this.betValue.equals(betValue);
	}

	

}
