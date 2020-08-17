package com.prueba.roulette.util;

import java.util.HashMap;

public class AppConstants {

	public static final String NOT_FOUND_MESSAGE = "El servidor no ha encontrado su solicitud.";
	public static final String BAD_REQUEST_MESSAGE = "El servidor no pudo entender la solicitud.";
	public static final String CREATED_MESSAGE = "La solicitud es correcta y se creara un nuevo servicio.";
	public static final String OK_MESSAGE = "La solicitud ha tenido éxito.";
	
	public static final String BLACK = "negro";
	public static final String RED = "rojo";
	public static final String WIN = "gana";
	public static final String LOSE = "pierde";
	
	public static final HashMap<Integer, String> ROULETTE_NUMBERS = new HashMap<>();

    static {
    	ROULETTE_NUMBERS.put(0, "");
    	for (int i = 1; i <= 36; i++) {
    		if (i%2 == 0 && (i <= 10 || (20 <= i && i <= 28))) {
    			ROULETTE_NUMBERS.put(i, BLACK);
    		} else {
    			ROULETTE_NUMBERS.put(i, RED);
    		}
		}
    }
}
