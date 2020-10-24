package com.banca.domain;

public class ContoItaliano extends ContoCorrente {

	private static final double TASSA_PERCENTUALE = 5;

	// Costruttore di ContoItaliano richiama quello della superclasse
	public ContoItaliano(int id, double saldo) {
		super(id, saldo);
	}

	@Override
	public void deposita(double amount) {
		double saldo = getSaldo();
		saldo += (1 - TASSA_PERCENTUALE / 100.0) * amount;
		setSaldo(saldo);
	}
}