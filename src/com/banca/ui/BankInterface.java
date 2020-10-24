package com.banca.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.banca.domain.*;
import com.banca.exception.SaldoInsufficienteException;

public class BankInterface {

	public static void main(String[] args) {

		Banca banca = Banca.getInstance();
		Scanner scanner = new Scanner(System.in);
		boolean t = true; // Flag per il ciclo while dell'interfaccia
		Iterable<Cliente> iterableClienti = banca.getClienti();

		// Report iniziale sui clienti
		List<Cliente> listaClienti = new ArrayList<Cliente>();
		iterableClienti.forEach(listaClienti::add);
		List<ReportCliente> listaReport = listaClienti.stream()
				.map(c -> new ReportCliente(c.getNome() + " " + c.getCognome(), c.getConti().size(),
						c.getConti().values().stream().mapToDouble(s -> s.getSaldo()).sum()))
				.collect(Collectors.toList());
		listaReport.forEach(System.out::println);

		while (t) {

			System.out.println("______________________________________________");
			System.out.println();

			for (Cliente c : iterableClienti) {
				System.out.println(c);
			}

			System.out.println("Cosa vuoi fare: \n 0 - Termina \n 1 - Preleva \n 2 - Deposita \n 3 - Bonifico");
			int n = scanner.nextInt();
			try {
				switch (n) {
				case 0: {
					System.out.println("Sessione terminata");
					t = false;
					break;
				}
				case 1: {
					DatiOperazione dati = acquisisciOperazioneSingola(scanner);
					banca.preleva(dati.amount, dati.idConto, dati.idCliente);
					break;
				}
				case 2: {
					DatiOperazione dati = acquisisciOperazioneSingola(scanner);
					banca.deposita(dati.amount, dati.idConto, dati.idCliente);
					break;
				}
				case 3: {
					DatiOperazione dati = acquisisciOperazioneDoppia(scanner);
					banca.bonifico(dati.amount, dati.idConto, dati.idCliente, dati.idContoDest, dati.idClienteDest);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + n);
				}
			} catch (SaldoInsufficienteException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static DatiOperazione acquisisciOperazioneSingola(Scanner sc) {
		DatiOperazione dati = new DatiOperazione();
		System.out.print("Inserisci Id cliente: ");
		dati.idCliente = sc.nextInt();
		System.out.print("Inserisci Id conto: ");
		dati.idConto = sc.nextInt();
		System.out.print("Inserisci ammontare: ");
		dati.amount = sc.nextInt();
		return dati;
	}

	public static DatiOperazione acquisisciOperazioneDoppia(Scanner sc) {
		DatiOperazione dati = acquisisciOperazioneSingola(sc);
		System.out.print("Inserisci Id cliente destinatario: ");
		dati.idClienteDest = sc.nextInt();
		System.out.print("Inserisci Id conto destinatario: ");
		dati.idContoDest = sc.nextInt();
		
		return dati;
	}
}
