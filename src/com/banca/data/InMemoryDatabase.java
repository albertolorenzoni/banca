package com.banca.data;

import java.util.ArrayList;
import java.util.List;

import com.banca.domain.Cliente;
import com.banca.domain.ContoCayman;
import com.banca.domain.ContoCorrente;
import com.banca.domain.ContoItaliano;
import com.banca.domain.Impiegato;
import com.banca.domain.Sesso;

public class InMemoryDatabase implements DatabaseInterface {

	private Iterable<Cliente> clients = readAllClients();

	// Restituisce la lista clienti
	public Iterable<Cliente> getAllClients() {
		return clients;
	}

	// Inizializza alcuni clienti e conti e li salva nell'iterable clients
	private Iterable<Cliente> readAllClients() {

		List<Cliente> clienti = new ArrayList<Cliente>();

		Cliente c1 = new Cliente(1, "Mario", "Rossi", 22, Sesso.MASCHIO);
		ContoCorrente cc1 = new ContoCayman(1, 100, "asdafaf");
		c1.aggiungiConto(cc1);
		ContoCorrente cc2 = new ContoItaliano(3, 300);
		c1.aggiungiConto(cc2);
		clienti.add(c1);
		Cliente c2 = new Cliente(2, "Paola", "Carlito", 24, Sesso.FEMMINA);
		ContoCorrente cc12 = new ContoCayman(15, 1000, "asdafar");
		c2.aggiungiConto(cc12);
		ContoCorrente cc22 = new ContoItaliano(36, 3000);
		c2.aggiungiConto(cc22);

		clienti.add(c2);

		return clienti;
	}

	@Override
	public Iterable<Impiegato> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}
}
