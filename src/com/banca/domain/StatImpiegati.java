package com.banca.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class StatImpiegati {

	private double sommaStipendi;
	private int numeroImpiegati;
	private double stipendioMinM = Double.MAX_VALUE;
	private double stipendioMaxF = 0;
	private List<Impiegato> impMaschiSotto25 = new ArrayList<Impiegato>();

	public StatImpiegati combina(Impiegato i) {
		sommaStipendi += i.getStipendio();
		numeroImpiegati++;

		if (i.isFemale()) {
			if (i.getStipendio() > stipendioMaxF) {
				stipendioMaxF = i.getStipendio();
			}
		} else {
			if (Period.between(i.getDataNascita(), LocalDate.now()).getYears() < 25) {
				impMaschiSotto25.add(i);
			}
			if (i.getStipendio() < stipendioMinM) {
				stipendioMinM = i.getStipendio();
			}
		}
		return this;
	}

	public List<Impiegato> getMaschiMin25() {
		return impMaschiSotto25;
	}

	public boolean genderWageGap() {
		return stipendioMaxF < stipendioMinM;
	}

	public double getStipendioMedio() {
		return sommaStipendi / numeroImpiegati;
	}

	public double getSommaStipendi() {
		return sommaStipendi;
	}

	public StatImpiegati combina(StatImpiegati other) {
		this.sommaStipendi += other.sommaStipendi;
		this.numeroImpiegati += other.numeroImpiegati;
		this.stipendioMaxF = Math.max(this.stipendioMaxF, other.stipendioMaxF);
		this.stipendioMinM = Math.min(this.stipendioMinM, other.stipendioMinM);
		other.impMaschiSotto25.forEach(i -> this.impMaschiSotto25.add(i));
		return this;
	}

}
