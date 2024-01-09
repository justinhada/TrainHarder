package de.justinharder.old.domain.services.berechnung;

import de.justinharder.old.domain.repository.KraftwertRepository;
import de.justinharder.old.domain.model.Benutzer;
import de.justinharder.old.domain.model.Konstanten;
import de.justinharder.old.domain.model.Kraftwert;
import de.justinharder.old.domain.model.enums.Geschlecht;
import de.justinharder.old.domain.model.enums.Kraftlevel;
import de.justinharder.old.domain.model.enums.Uebungsart;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Dependent
@RequiredArgsConstructor
public class KraftlevelErmittlung
{
	@NonNull
	private final KraftwertRepository kraftwertRepository;

	public Kraftlevel ermittle(@NonNull Benutzer benutzer)
	{
		var gewichtsklasse =
			ermittleGewichtsklasse(benutzer.getBenutzerangabe().getGeschlecht(), benutzer.getKoerpergewicht());
		var total = ermittleTotal(kraftwertRepository.findeAlleMitBenutzer(benutzer.getId()));
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
		return (geschlecht.equals(Geschlecht.WEIBLICH) ? Konstanten.TOTALS_FRAUEN : Konstanten.TOTALS_MAENNER)
			.get(gewichtsklasse);
	}

	private int ermittleGewichtsklasse(Geschlecht geschlecht, BigDecimal koerpergewicht)
	{
		return Collections.min(ermittleGewichtsklassen(geschlecht).stream()
			.filter(gk -> gk >= koerpergewicht.intValueExact())
			.toList());
	}

	private List<Integer> ermittleGewichtsklassen(Geschlecht geschlecht)
	{
		return geschlecht.equals(Geschlecht.WEIBLICH)
			? Konstanten.GEWICHTSKLASSEN_FRAUEN
			: Konstanten.GEWICHTSKLASSEN_MAENNER;
	}
}
