package com.banca.domain;

import java.util.Map;
import java.util.HashMap;
import com.banca.domain.*;

public class Cliente {

	private String nome;
	private String cognome;
	private int eta;
	private int idCliente;
	private Sesso sesso; // Il tipo di un enum è il nome dell'enum
	private Map<Integer, ContoCorrente> conti; // Struttura dati che salva i suoi elementi come coppie chiave-valore

	// Costruttore
	public Cliente(int idCliente, String nome, String cognome, int eta, Sesso sesso) {
		this.idCliente = idCliente;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.sesso = sesso;
		conti = new HashMap<Integer, ContoCorrente>();
	}

	public void aggiungiConto(ContoCorrente c) {
		conti.put(c.getIdConto(), c); // Aggiungo il conto c alla mappa di conti del cliente, usando idConto come
										// chiave
	}

	public ContoCorrente rimuoviConto(int idConto) {
		ContoCorrente conto = conti.get(idConto);
		if (conto != null) {
			conto.setCliente(null);
			conti.remove(idConto); // Rimuove la coppia chiave valore dalla mappa
		}
		return conto; // Ritorna il conto corrente eliminato
	}

	@Override
	public String toString() {
		// Usiamo StringBuilder e una serie di append concatenati per costruire la
		// stringa
		StringBuilder sb = new StringBuilder();

		sb.append(" ID Cliente: ").append(idCliente).append(" - Nome Cliente: ").append(nome).append(" - Cognome Cliente: ")
				.append(cognome).append(System.lineSeparator());

		for (ContoCorrente c : conti.values()) {
			sb.append(" ID Conto: ").append(c.getIdConto()).append(" - Saldo Conto: ").append(c.getSaldo())
					.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public int getId() {
		return idCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public Map<Integer, ContoCorrente> getConti() {
		return conti;
	}

	public ContoCorrente getContoById(int idConto) {
		return conti.get(idConto);
	}

}