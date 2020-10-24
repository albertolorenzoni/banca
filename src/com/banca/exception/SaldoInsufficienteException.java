package com.banca.exception;

import com.banca.domain.*;

public class SaldoInsufficienteException extends Exception {
	private int idConto;
	private double saldo;
	private double amount;

	// Costruttore
	public SaldoInsufficienteException(double saldo, double amount, int idConto) {

		super("Tentativo di ritiro con fondi non sufficienti sul conto.\n" + "ID Conto: " + idConto + " Saldo: " + saldo
				+ "Prelievo   " + amount);
		this.saldo = saldo;
		this.amount = amount;
		this.idConto = idConto;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public double getAmount() {
		return this.amount;
	}

	public double getId() {
		return this.idConto;
	}
}