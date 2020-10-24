package com.banca.domain;

public class ContoCayman extends ContoCorrente {

	private String codiceSegreto;

	private static final double BONUS = 10;

	public ContoCayman(int idConto, double saldo, String codiceSegreto) {
		super(idConto, saldo);
		this.codiceSegreto = codiceSegreto;
	}

	@Override
	public void deposita(double amount) {
		double saldo = getSaldo();
		if (Banca.getInstance().verificaCodice(codiceSegreto)) {
			saldo += amount * (1 + BONUS / 100.0);
		} else {
			saldo += amount;
		}
		setSaldo(saldo);
	}

}
