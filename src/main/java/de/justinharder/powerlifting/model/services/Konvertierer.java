package de.justinharder.powerlifting.model.services;

import java.util.List;
import java.util.stream.Collectors;

import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.dto.BelastungsfaktorEintrag;
import de.justinharder.powerlifting.model.domain.dto.KraftwertEintrag;
import de.justinharder.powerlifting.model.domain.dto.UebungEintrag;

public class Konvertierer
{
	private Konvertierer()
	{}

	public static List<UebungEintrag> konvertiereAlleZuUebungEintrag(final List<Uebung> uebungen)
	{
		return uebungen
			.stream()
			.map(Konvertierer::konvertiereZuUebungEintrag)
			.collect(Collectors.toList());
	}

	public static UebungEintrag konvertiereZuUebungEintrag(final Uebung uebung)
	{
		return new UebungEintrag(
			uebung.getId(),
			uebung.getName(),
			uebung.getUebungsart().name(),
			uebung.getUebungskategorie().name());
	}

	public static Belastungsfaktor konvertiereZuBelastungsfaktor(final BelastungsfaktorEintrag belastungsfaktorEintrag)
	{
		return new Belastungsfaktor(
			belastungsfaktorEintrag.getSquat(),
			belastungsfaktorEintrag.getBenchpress(),
			belastungsfaktorEintrag.getDeadlift(),
			belastungsfaktorEintrag.getTriceps(),
			belastungsfaktorEintrag.getChest(),
			belastungsfaktorEintrag.getCore(),
			belastungsfaktorEintrag.getBack(),
			belastungsfaktorEintrag.getBiceps(),
			belastungsfaktorEintrag.getGlutes(),
			belastungsfaktorEintrag.getQuads(),
			belastungsfaktorEintrag.getHamstrings(),
			belastungsfaktorEintrag.getShoulder());
	}

	public static BelastungsfaktorEintrag konvertiereZuBelastungsfaktorEintrag(final Belastungsfaktor belastungsfaktor)
	{
		return new BelastungsfaktorEintrag(
			belastungsfaktor.getId(),
			belastungsfaktor.getSquat(),
			belastungsfaktor.getBenchpress(),
			belastungsfaktor.getDeadlift(),
			belastungsfaktor.getTriceps(),
			belastungsfaktor.getChest(),
			belastungsfaktor.getCore(),
			belastungsfaktor.getBack(),
			belastungsfaktor.getBiceps(),
			belastungsfaktor.getGlutes(),
			belastungsfaktor.getQuads(),
			belastungsfaktor.getHamstrings(),
			belastungsfaktor.getShoulder());
	}

	public static List<KraftwertEintrag> konvertiereAlleZuKraftwertEintrag(final List<Kraftwert> kraftwerte)
	{
		return kraftwerte
			.stream()
			.map(Konvertierer::konvertiereZuKraftwertEintrag)
			.collect(Collectors.toList());
	}

	public static KraftwertEintrag konvertiereZuKraftwertEintrag(final Kraftwert kraftwert)
	{
		return new KraftwertEintrag(
			kraftwert.getId(),
			kraftwert.getMaximum(),
			kraftwert.getKoerpergewicht(),
			kraftwert.getDatum(),
			kraftwert.getWiederholungen().name());
	}
}
