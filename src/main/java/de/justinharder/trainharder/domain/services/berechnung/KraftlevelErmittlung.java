package de.justinharder.trainharder.domain.services.berechnung;

import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.Konstanten;
import de.justinharder.trainharder.domain.model.Kraftwert;
import de.justinharder.trainharder.domain.model.enums.Geschlecht;
import de.justinharder.trainharder.domain.model.enums.Kraftlevel;
import de.justinharder.trainharder.domain.model.enums.Uebungsart;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KraftlevelErmittlung
{
	public Kraftlevel ermittle(@NonNull Benutzer benutzer)
	{
		var gewichtsklasse = ermittleGewichtsklasse(benutzer.getBenutzerangabe().getGeschlecht(), benutzer.getKoerpergewicht());
		var total = ermittleTotal(benutzer.getKraftwerte());
		var totals = ermittleTotals(benutzer.getBenutzerangabe().getGeschlecht(), gewichtsklasse);
		var uebertroffeneTotals = totals.keySet().stream()
			.filter(t -> Double.compare(t, total.doubleValue()) < 0)
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

	private BigDecimal ermittleTotal(List<Kraftwert> kraftwerte)
	{
		return kraftwerte.stream()
			.filter(kraftwert -> kraftwert.getUebung().getUebungsart().equals(Uebungsart.GRUNDUEBUNG))
			.map(Kraftwert::getGewicht)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private Map<Integer, Kraftlevel> ermittleTotals(Geschlecht geschlecht, int gewichtsklasse)
	{
		return (geschlecht.equals(Geschlecht.WEIBLICH) ? Konstanten.TOTALS_FRAUEN : Konstanten.TOTALS_MAENNER).get(gewichtsklasse);
	}

	private int ermittleGewichtsklasse(Geschlecht geschlecht, BigDecimal koerpergewicht)
	{
		return Collections.min(ermittleGewichtsklassen(geschlecht).stream()
			.filter(gk -> gk >= koerpergewicht.intValueExact())
			.collect(Collectors.toList()));
	}

	private List<Integer> ermittleGewichtsklassen(Geschlecht geschlecht)
	{
		return geschlecht.equals(Geschlecht.WEIBLICH) ? Konstanten.GEWICHTSKLASSEN_FRAUEN : Konstanten.GEWICHTSKLASSEN_MAENNER;
	}
}
