package com.banca.domain;

import java.time.LocalDate;

public class Impiegato {

	private int idImpiegato;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private Sesso sesso;
	private double stipendio;
	
	public Impiegato(int idImpiegato, String nome, String cognome, LocalDate dataNascita, Sesso sesso, double stipendio) {
		super();
		this.idImpiegato = idImpiegato;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.stipendio = stipendio;
	}
	
	@Override
	public String toString() {
		return "Impiegato [idImpiegato=" + idImpiegato + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita="
				+ dataNascita + ", sesso=" + sesso + ", stipendio=" + stipendio + "]";
	}

	public int getIdImpiegato() {
		return idImpiegato;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public double getStipendio() {
		return stipendio;
	}
	
	public boolean isFemale() {
		return this.sesso == Sesso.FEMMINA;
	}
	

}
