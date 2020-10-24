package com.banca.data;

import com.banca.domain.Cliente;

public interface DatabaseInterface {

	public Iterable<Cliente> getAllClients();

}
