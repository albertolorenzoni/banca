package com.banca.ui;

import java.util.ArrayList;
import java.util.List;

import com.banca.domain.Banca;
import com.banca.domain.Impiegato;

public class InterfacciaImpiegati {
	public static void main(String[] args) {
		Banca banca = Banca.getInstance();
		List<Impiegato> impiegati = new ArrayList<Impiegato>();
		
		banca.getImpiegati().forEach(impiegati::add); //Creo una list dall'iterable in modo da poter usare stream
		
		
		
		
	}
}
