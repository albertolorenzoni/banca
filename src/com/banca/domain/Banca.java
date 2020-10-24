package com.banca.domain;

import com.banca.data.DatabaseInterface;
import com.banca.data.InMemoryDatabase;
import com.banca.exception.SaldoInsufficienteException;

// Singleton della classe banca. Tutte le operazioni sui conti vengono chiamate passando 
// dall'istanza di Banca
public class Banca {

	private static Banca instance = new Banca();
	private String nome = "Bank of Java";
	private String[] codiciSegreti = { "adfhfda", "asdafaf", "zxcxv" };

	private DatabaseInterface database = new InMemoryDatabase();

	// Singleton -> Costruttore privato
	private Banca() {
	}

	public Iterable<Cliente> getClienti() {
		return database.getAllClients();
	}

	// getInstance restituisce l'unica instance del singleton Banca
	// necessario perché il costruttore è privato per assicurarsi che non venga
	// chiamato da fuori
	public static Banca getInstance() {
		return instance;
	}

	// Verifica il codice di un ContoCayman
	public boolean verificaCodice(String codiceSegreto) {
		for (String codice : codiciSegreti) {
			if (codiceSegreto.equals(codice)) {
				return true;
			}
		}
		return false;
	}

	public void preleva(double amount, int idConto, int idCliente) throws SaldoInsufficienteException {
		for (Cliente cliente : database.getAllClients()) {
			int id = cliente.getId();
			if (idCliente == id) {
				ContoCorrente x = cliente.getContoById(idConto);
				x.preleva(amount);
				return;
			}
		}

	}

	public void deposita(double deposito, int idConto, int idCliente) {
		for (Cliente cliente : database.getAllClients()) {
			int id = cliente.getId();
			if (idCliente == id) {
				ContoCorrente x = cliente.getContoById(idConto);
				x.deposita(deposito);
				return;
			}
		}

	}

	public void bonifico(double amount, int idContoSorgente, int idClienteSorgente, int idContoDestinatario,
			int idClienteDestinatario) throws SaldoInsufficienteException {

		Cliente sorgente = null;
		Cliente destinatario = null;

		for (Cliente c : database.getAllClients()) {
			int id = c.getId();
			if (idClienteSorgente == id) {
				sorgente = c;
			} else if (idClienteDestinatario == id) {
				destinatario = c;
			}
		}
		sorgente.getContoById(idContoSorgente).bonifico(amount, destinatario.getContoById(idContoDestinatario));
	}

}
