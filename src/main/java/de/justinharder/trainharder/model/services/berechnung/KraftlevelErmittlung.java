package de.justinharder.trainharder.model.services.berechnung;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Konstanten;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Kraftlevel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KraftlevelErmittlung
{
	public Kraftlevel ermittle(Benutzer benutzer)
	{
		var gewichtsklasse = ermittleGewichtsklasse(
			benutzer.getBenutzerangabe().getGeschlecht(),
			benutzer.getKoerpergewicht());
		var total = ermittleTotal(benutzer.getKraftwerte());
		var totals = ermittleTotals(benutzer.getBenutzerangabe().getGeschlecht(), gewichtsklasse);
		var uebertroffeneTotals = totals.keySet().stream()
			.filter(t -> Double.compare(t, total) < 0)
			.collect(Collectors.toList());

		if (uebertroffeneTotals.isEmpty())
		{
			return Kraftlevel.CLASS_5;
		}
		return ermittleKraftlevel(totals, uebertroffeneTotals);
	}

	private Kraftlevel ermittleKraftlevel(Map<Integer, Kraftlevel> totals, List<Integer> uebertroffeneTotals)
	{
		return totals.get(Collections.max(uebertroffeneTotals));
	}

	private double ermittleTotal(List<Kraftwert> kraftwerte)
	{
		return kraftwerte.stream()
			.filter(kraftwert -> kraftwert.getUebung().getUebungsart().equals(Uebungsart.GRUNDUEBUNG))
			.mapToDouble(Kraftwert::getGewicht)
			.sum();
	}

	private Map<Integer, Kraftlevel> ermittleTotals(Geschlecht geschlecht, int gewichtsklasse)
	{
		return (geschlecht.equals(Geschlecht.WEIBLICH) ? Konstanten.TOTALS_FRAUEN : Konstanten.TOTALS_MAENNER)
			.get(gewichtsklasse);
	}

	private int ermittleGewichtsklasse(Geschlecht geschlecht, double koerpergewicht)
	{
		return Collections.min(ermittleGewichtsklassen(geschlecht).stream()
			.filter(gk -> gk >= koerpergewicht)
			.collect(Collectors.toList()));
	}

	private List<Integer> ermittleGewichtsklassen(Geschlecht geschlecht)
	{
		return geschlecht.equals(Geschlecht.WEIBLICH)
			? Konstanten.GEWICHTSKLASSEN_FRAUEN : Konstanten.GEWICHTSKLASSEN_MAENNER;
	}
}
