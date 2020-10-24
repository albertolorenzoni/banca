package com.banca.domain;

import com.banca.exception.*;

public abstract class ContoCorrente {

	private int idConto;
	private double saldo;
	private Cliente cliente;

	// Costruttore
	public ContoCorrente(int idConto, double saldo) {
		this.idConto = idConto;
		this.saldo = saldo;
	}

	public int getIdConto() {
		return this.idConto;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	// Metodo astratto deposita
	public abstract void deposita(double amount);

	public void preleva(double amount) throws SaldoInsufficienteException {
		if (amount > this.saldo) {
			throw new SaldoInsufficienteException(this.saldo, amount, this.idConto);
		}
		this.saldo -= amount;
	}

	public void setCliente(Cliente c) {
		this.cliente = c;
	}

	public void bonifico(double amount, ContoCorrente destinatario) throws SaldoInsufficienteException {
		this.preleva(amount);
		destinatario.deposita(amount);
	}

}