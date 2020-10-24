package com.banca.data;

import com.banca.domain.Cliente;
import com.banca.domain.Impiegato;

public interface DatabaseInterface {

	public Iterable<Cliente> getAllClients();
	
	public Iterable<Impiegato> getAllEmployees();

}
