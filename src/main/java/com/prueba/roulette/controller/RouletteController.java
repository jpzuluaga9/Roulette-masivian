package com.prueba.roulette.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.roulette.data.ResponseBody;
import com.prueba.roulette.data.RouletteDATA;
import com.prueba.roulette.model.Bet;
import com.prueba.roulette.model.Roulette;
import com.prueba.roulette.services.RouletteService;
import com.prueba.roulette.util.AppConstants;

@RestController
@RequestMapping("/ruleta")
public class RouletteController {

	@Autowired
	private RouletteService rouletteService;

	@GetMapping
	public ResponseEntity<ResponseBody> getRoulettes() {
		ResponseBody response = new ResponseBody(HttpStatus.OK, AppConstants.OK_MESSAGE);
		response.addPayload("ruletas", rouletteService.getRoulettes());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ResponseBody> createRoulette() {
		ResponseBody response = new ResponseBody(HttpStatus.CREATED, AppConstants.CREATED_MESSAGE);
		response.addPayload("idRuleta", rouletteService.createRoulette());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/apostar")
	public ResponseEntity<ResponseBody> gamble(@RequestHeader(value = "id-usuario") String idUser,
			@RequestBody Bet bet) {

		Optional<Roulette> roulette = rouletteService.gamble(bet);
		if (roulette == null) {
			ResponseBody response = new ResponseBody(HttpStatus.BAD_REQUEST, AppConstants.BAD_REQUEST_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else if (!roulette.isPresent()) {
			ResponseBody response = new ResponseBody(HttpStatus.NOT_FOUND, AppConstants.NOT_FOUND_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		ResponseBody response = new ResponseBody(HttpStatus.OK, AppConstants.OK_MESSAGE);
		response.addPayload("ruleta", roulette.get());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/apertura/{id}")
	public ResponseEntity<ResponseBody> openRoulette(@PathVariable("id") String id) {
		Optional<Roulette> roulette = rouletteService.openRoulette(id);
		if (!roulette.isPresent()) {
			ResponseBody response = new ResponseBody(HttpStatus.NOT_FOUND, AppConstants.NOT_FOUND_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		ResponseBody response = new ResponseBody(HttpStatus.OK, AppConstants.OK_MESSAGE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	@PutMapping(value = "/cierre/{id}")
	public ResponseEntity<ResponseBody> closeRoulette(@PathVariable("id") String id) {
		RouletteDATA rouletteDTO = rouletteService.closeRoulette(id);
		if (rouletteDTO == null) {
			ResponseBody response = new ResponseBody(HttpStatus.NOT_FOUND, AppConstants.NOT_FOUND_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		ResponseBody response = new ResponseBody(HttpStatus.OK, AppConstants.OK_MESSAGE);
		response.addPayload("resultados", rouletteDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
