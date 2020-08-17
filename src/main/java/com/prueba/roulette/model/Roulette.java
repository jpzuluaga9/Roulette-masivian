package com.prueba.roulette.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonProperty;

@RedisHash("ruleta")
public class Roulette {

	@Id
	private String id;
	
	@Indexed
	@JsonProperty("estado")
	private String state;
	
	@JsonProperty("apuestas")
	private List<Bet> bets;
	
	public Roulette() {
		super();
		this.bets = new ArrayList<>();
	}

	public Roulette(String id, String state, List<Bet> bets) {
		super();
		this.id = id;
		this.state = state;
		this.bets = bets;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Bet> getBets() {
		return bets;
	}

	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}
	
	public void addBet(Bet bet) {
		this.bets.add(bet);
	}
	
}
