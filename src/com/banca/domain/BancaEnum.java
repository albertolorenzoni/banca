package com.banca.domain;

// Equivalente ad un singleton di classe Banca

public enum BancaEnum {
	
	BANK_OF_JAVA("La miglior banca per i programmatori Java");
	
	private String nome;
	
	private BancaEnum(String s) {
		this.nome=s;
	}

}
