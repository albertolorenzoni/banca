package com.banca.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

import com.banca.domain.Cliente;
import com.banca.domain.Impiegato;
import com.banca.domain.Sesso;

public class FileSystemDatabase implements DatabaseInterface {

	@Override
	public Iterable<Cliente> getAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Impiegato> getAllEmployees() {
		List<Impiegato> impiegati = new ArrayList<Impiegato>();

		BufferedReader in;
//		try {
//			in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("impiegati.txt")));
//			String newLine = "";
//			while ((newLine = in.readLine()) != null) {
//				System.out.println(newLine);
//				String[] campi = new String[6];
//				campi = newLine.split(",");
//				impiegati.add(new Impiegato(Integer.parseInt(campi[0]), campi[1], campi[2], LocalDate.parse(campi[3]),
//						Sesso.valueOf(campi[4]), Double.parseDouble(campi[5])));
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("impiegati.txt")));
		
		impiegati = in.lines().map(line -> line.split(","))
				.map(fields -> new Impiegato(Integer.parseInt(fields[0]), fields[1], fields[2],
						LocalDate.parse(fields[3]), Sesso.valueOf(fields[4]), Double.parseDouble(fields[5])))
				.collect(Collectors.toList());

//		impiegati.forEach(System.out::println);

		return impiegati;
	}

}
