package com.banca.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StatImpiegati {

	private double sommaStipendi;
	private int numeroImpiegati;
	private double stipendioMinM = Double.MAX_VALUE;
	private double stipendioMaxF = 0;
	private List<Impiegato> impMaschiSotto25 = new ArrayList<Impiegato>();
	List<Impiegato> impiegati = new ArrayList<Impiegato>();
	private double mediana = 0;
	private Map<Double, Integer> freqStipendi = new HashMap<Double, Integer>();
	private double modaStipendi = 0;


	public static StatImpiegati combina(StatImpiegati s,Impiegato i) {
		StatImpiegati statCombin = new StatImpiegati();
		statCombin.sommaStipendi =s.getSommaStipendi() + i.getStipendio();
		statCombin.numeroImpiegati =s.numeroImpiegati +1;
		statCombin.impiegati.addAll(s.impiegati);
		
		statCombin.impiegati.add(i);
		
		statCombin.freqStipendi.putAll(s.freqStipendi);
		
		if(statCombin.freqStipendi.containsKey(i.getStipendio())) {
			statCombin.freqStipendi.replace(i.getStipendio(), statCombin.freqStipendi.get(i.getStipendio())+1);
		}
		else {
			statCombin.freqStipendi.put(i.getStipendio(), 1);
		}
		statCombin.stipendioMaxF = s.stipendioMaxF;
		statCombin.stipendioMinM = s.stipendioMinM;
		statCombin.impMaschiSotto25.addAll(s.impMaschiSotto25);
		if (i.isFemale()) {
			if (i.getStipendio() > statCombin.stipendioMaxF) {
				statCombin.stipendioMaxF = i.getStipendio();
			}
		} else {
			if (Period.between(i.getDataNascita(), LocalDate.now()).getYears() < 25) {
				statCombin.impMaschiSotto25.add(i);
			}
			if (i.getStipendio() < statCombin.stipendioMinM) {
				statCombin.stipendioMinM = i.getStipendio();
			}
		}
	
		return statCombin;
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
	
	public void calcoloModa() {
		this.modaStipendi = freqStipendi.entrySet().stream()
				.sorted((kv1, kv2) -> kv2.getValue() - kv1.getValue()).findFirst().get().getKey();
		
//		freqStipendi.keySet().forEach(k -> {
//			if (freqStipendi.get(k) > this.freqMax) {
//				this.modaStipendi = k;
//				this.freqMax = freqStipendi.get(k);
//			}
//		});

	}
	
	public double getModa() {
		if(modaStipendi == 0) {
			this.calcoloModa();
		}
		return this.modaStipendi;
	}
	

	public void calcoloMediana() {
		
		
		impiegati.sort((i1,i2)->(int) Math.signum(i1.getStipendio() - i2.getStipendio()));

		if (impiegati.size() % 2 == 0) {
			double s1 = impiegati.get(impiegati.size() / 2 - 1).getStipendio();
			double s2 = impiegati.get(impiegati.size() / 2).getStipendio();
			this.mediana = (s1 + s2) / 2;
		} else {
			this.mediana = impiegati.get(impiegati.size() / 2).getStipendio();
		}
		
		
	}
	
	public double getMediana() {
		if(mediana == 0) {
			this.calcoloMediana();
		}
		return this.mediana;
	}
	
	

	public Map<Double, Integer> getFreqStipendi() {
		return freqStipendi;
	}

	public static StatImpiegati combina(StatImpiegati s1, StatImpiegati s2) {
		StatImpiegati statCombin = new StatImpiegati();
		statCombin.sommaStipendi = s1.sommaStipendi + s2.sommaStipendi;
		statCombin.numeroImpiegati =s1.numeroImpiegati + s2.numeroImpiegati;
		
		statCombin.stipendioMaxF = Math.max(s1.stipendioMaxF, s2.stipendioMaxF);
		statCombin.stipendioMinM = Math.min(s1.stipendioMinM, s2.stipendioMinM);
		statCombin.impMaschiSotto25.addAll(s1.impMaschiSotto25);
		statCombin.impMaschiSotto25.addAll(s2.impMaschiSotto25);
		
		statCombin.impiegati.addAll(s1.impiegati);
		statCombin.impiegati.addAll(s2.impiegati);
		
		
		statCombin.freqStipendi.putAll(s1.freqStipendi);
		s2.freqStipendi.forEach((k,v) -> statCombin.freqStipendi.merge(k, v, (v1, v2) -> v1 + v2));

		statCombin.calcoloMediana();
		statCombin.calcoloModa();
		return statCombin;
	}

}
