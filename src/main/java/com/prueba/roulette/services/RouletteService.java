package com.prueba.roulette.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.roulette.data.BetDATA;
import com.prueba.roulette.data.RouletteDATA;
import com.prueba.roulette.model.Bet;
import com.prueba.roulette.model.Roulette;
import com.prueba.roulette.repository.RouletteRepository;
import com.prueba.roulette.util.State;
import com.prueba.roulette.util.AppConstants;

@Service
@Transactional
public class RouletteService {

	@Autowired
	private RouletteRepository rouletteRepository;

	public List<Roulette> getRoulettes() {
		List<Roulette> roulettes = new ArrayList<>();
		rouletteRepository.findAll().forEach(roulettes::add);
		
		return roulettes;
	}

	public String createRoulette() {
		Roulette roulette = new Roulette();
		roulette.setState(State.CLOSED.getRealName());

		return rouletteRepository.save(roulette).getId();
	}

	public Optional<Roulette> openRoulette(String id) {
		Optional<Roulette> roulette = rouletteRepository.findById(id);
		roulette.ifPresent(r -> {
			r.setState(State.OPEN.getRealName());
			rouletteRepository.save(r);
		});
		return roulette;
	}

	public Optional<Roulette> gamble(Bet bet) {
		if (bet != null && bet.isValid()) {
			Optional<Roulette> roulette = rouletteRepository.findByState(State.OPEN.getRealName());
			roulette.ifPresent(r -> {
				r.addBet(bet);
				rouletteRepository.save(r);
			});
			return roulette;
		}
		
		return null;
	}

	public RouletteDATA closeRoulette(String id) {
		Optional<Roulette> rouletteOptional = rouletteRepository.findById(id);

		if (rouletteOptional.isPresent()) {
			Roulette roulette = rouletteOptional.get();

			Integer random = (int) (Math.random() * 36);
			String color = AppConstants.ROULETTE_NUMBERS.get(random);
			String result = String.format("%s %s", random, color);
			
			List<BetDATA> bets = new ArrayList<>();
			for (Bet bet : roulette.getBets()) {
				if (bet.isWinner(color) || bet.isWinner(random.toString())) {
					bets.add(new BetDATA(bet.getBetValue(), bet.getAmount(), AppConstants.WIN));
				} else {
					bets.add(new BetDATA(bet.getBetValue(), bet.getAmount(), AppConstants.LOSE));
				}
			}
			
			roulette.setState(State.CLOSED.getRealName());
			roulette.setBets(new ArrayList<>());
			rouletteRepository.save(roulette);

			return new RouletteDATA(result, bets);
		}

		return null;
	}
}
