package com.banca.ui;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.banca.domain.Banca;
import com.banca.domain.Impiegato;
import com.banca.domain.Sesso;
import com.banca.domain.StatImpiegati;


public class InterfacciaImpiegati {
	public static void main(String[] args) {
		
		Banca banca = Banca.getInstance();
		List<Impiegato> impiegati = new ArrayList<Impiegato>();

		banca.getImpiegati().forEach(impiegati::add); // Creo una list dall'iterable in modo da poter usare stream
		
		
//		System.out.println("Statisctiche calcolate con metodi imperativi:");
//		statistiche(impiegati);
//		
//		System.out.println("________________________________________________________________");
//		
//		System.out.println("Statisctiche calcolate con metodi funzionali:");
//		statisticheFunzionali(impiegati);
//		
//		System.out.println("________________________________________________________________");
		
		System.out.println("Statisctiche calcolate con metodo reduce:");
		statisticheReduce(impiegati);

	}

	private static void statisticheReduce(List<Impiegato> impiegati) {
		StatImpiegati statistiche = impiegati.stream().parallel()
				.reduce(new StatImpiegati(), (si, i) -> StatImpiegati.combina(si, i), 
				(si1, si2) -> StatImpiegati.combina(si1, si2));
		
		
				
		System.out.println("Totale stipendi: " + statistiche.getSommaStipendi());
		System.out.println("Media stipendi: " + statistiche.getStipendioMedio());
		System.out.println("Mediana stipendi: " + statistiche.getMediana());
		System.out.println("Moda stipendi: " + statistiche.getModa());
		System.out.println("Divario retributivo di genere? " + statistiche.genderWageGap());
		System.out.println("Impiegati maschi al di sotto dei 25 anni:");
		statistiche.getMaschiMin25().forEach(System.out::println);
	}

	private static void statisticheFunzionali(List<Impiegato> impiegati) {
		
		double sommaStipendi = impiegati.stream().mapToDouble(i -> i.getStipendio()).sum();
		double mediaStipendi = impiegati.stream().mapToDouble(i -> i.getStipendio()).average().orElse(0);
		double medianaStipendi = impiegati.stream()
				.sorted((i1, i2) -> ((int) Math.signum(i1.getStipendio() - i2.getStipendio())))
				.skip(Math.max(0, ((impiegati.size() + 1) / 2) - 1)).limit(1 + (1 + impiegati.size()) % 2)
				.mapToDouble(i -> i.getStipendio()).average().orElse(0);
		boolean genderWageGap = impiegati.stream()
				.sorted((i1, i2) -> ((int) Math.signum(i1.getStipendio() - i2.getStipendio())))
				.dropWhile(Impiegato::isFemale)
				.allMatch(i -> ! i.isFemale());
		List<Impiegato> impMaschiSotto25 = impiegati.stream()
				.filter(i -> i.getSesso() == Sesso.MASCHIO)
				.filter(i -> Period.between(i.getDataNascita(), LocalDate.now()).getYears() < 25)
				.collect(Collectors.toList());
		
		
		
		
		System.out.println("Totale stipendi: " + sommaStipendi);
		System.out.println("Media stipendi: " + mediaStipendi);
		System.out.println("Mediana stipendi: " + medianaStipendi);
		System.out.println("Divario retributivo di genere? " + genderWageGap);
		System.out.println("Impiegati maschi al di sotto dei 25 anni:");
		impMaschiSotto25.forEach(System.out::println);		
	}

	private static void statistiche(List<Impiegato> impiegati) {
		
		double sommaStipendi = 0;
		double mediaStipendi = 0;
		double medianaStipendi = 0;
		double stipendioMinM = Double.MAX_VALUE;
		double stipendioMaxF = 0;
		boolean genderWageGap = false;
		List<Impiegato> impMaschiSotto25 = new ArrayList<Impiegato>();
		
		impiegati.sort((i1, i2) -> ((int) Math.signum(i1.getStipendio() - i2.getStipendio())));

		for (Impiegato i : impiegati) {
			sommaStipendi += i.getStipendio();
			if (i.getSesso() == Sesso.FEMMINA && i.getStipendio() > stipendioMaxF) {
				stipendioMaxF = i.getStipendio();
			}
			if (i.getSesso() == Sesso.MASCHIO) {
				if (i.getStipendio() < stipendioMinM) {
					stipendioMinM = i.getStipendio();
				}
				if ((Period.between(i.getDataNascita(), LocalDate.now()).getYears()) < 25) {
					impMaschiSotto25.add(i);
				}
			}
		}

		mediaStipendi = sommaStipendi / impiegati.size();

		if (impiegati.size() % 2 == 0) {
			double s1 = impiegati.get(impiegati.size() / 2 - 1).getStipendio();
			double s2 = impiegati.get(impiegati.size() / 2).getStipendio();
			medianaStipendi = (s1 + s2) / 2;
		} else {
			medianaStipendi = impiegati.get(impiegati.size() / 2).getStipendio();
		}
		
		genderWageGap = stipendioMinM > stipendioMaxF;

		System.out.println("Totale stipendi: " + sommaStipendi);
		System.out.println("Media stipendi: " + mediaStipendi);
		System.out.println("Mediana stipendi: " + medianaStipendi);
		System.out.println("Divario retributivo di genere? " + genderWageGap);
		System.out.println("Impiegati maschi al di sotto dei 25 anni:");
		impMaschiSotto25.forEach(System.out::println);
	}
}
