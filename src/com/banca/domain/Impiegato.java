package com.banca.domain;

import java.time.LocalDate;

public class Impiegato {

	private int idImpiegato;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private Sesso sesso;
	private double salario;
	
	public Impiegato(int idImpiegato, String nome, String cognome, LocalDate dataNascita, Sesso sesso, double salario) {
		super();
		this.idImpiegato = idImpiegato;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.salario = salario;
	}
	
	@Override
	public String toString() {
		return "Impiegato [idImpiegato=" + idImpiegato + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita="
				+ dataNascita + ", sesso=" + sesso + ", salario=" + salario + "]";
	}
	

}
