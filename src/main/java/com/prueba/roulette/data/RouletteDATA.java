package com.prueba.roulette.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RouletteDATA {

	@JsonProperty("apuestas")
	private List<BetDATA> bets;
	
	@JsonProperty("resultado")
	private String result;
	
	public RouletteDATA() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RouletteDATA(String resultado, List<BetDATA> apuestas) {
		super();
		this.result = resultado;
		this.bets = apuestas;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<BetDATA> getBets() {
		return bets;
	}

	public void setBets(List<BetDATA> bets) {
		this.bets = bets;
	}
	
}
