package com.banca.domain;

public class ReportCliente {
	private String nomeCognome;
	private int numeroConti;
	private double totaleSaldi;

	public ReportCliente() {
	}

	public ReportCliente(String nomeCognome, int numeroConti, double totaleSaldi) {
		this.nomeCognome = nomeCognome;
		this.numeroConti = numeroConti;
		this.totaleSaldi = totaleSaldi;
	}

	public String toString() {
		return new String(
				"Cliente: " + nomeCognome + " - Numero conti: " + numeroConti + " - Saldo totale: " + totaleSaldi);
	}
}
