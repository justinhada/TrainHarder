package de.justinharder.trainharder.model.services;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;
import de.justinharder.trainharder.view.dto.BenutzerDto;
import de.justinharder.trainharder.view.dto.KraftwertDto;
import de.justinharder.trainharder.view.dto.UebungDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Konvertierer
{
	private static final String DATUMSFORMAT = "dd.MM.yyyy";

	public static List<AuthentifizierungDto> konvertiereAlleZuAuthentifizierungDto(
		final List<Authentifizierung> authentifizierungen)
	{
		return authentifizierungen
			.stream()
			.map(Konvertierer::konvertiereZuAuthentifizierungDto)
			.collect(Collectors.toList());
	}

	public static AuthentifizierungDto konvertiereZuAuthentifizierungDto(
		final Authentifizierung authentifizierung)
	{
		return new AuthentifizierungDto(
			authentifizierung.getPrimaerschluessel().getId().toString(),
			authentifizierung.getMail(),
			authentifizierung.getBenutzername(),
			authentifizierung.getPasswort());
	}

	public static List<BenutzerDto> konvertiereAlleZuBenutzerDto(final List<Benutzer> alleBenutzer)
	{
		return alleBenutzer.stream()
			.map(Konvertierer::konvertiereZuBenutzerDto)
			.collect(Collectors.toList());
	}

	public static BenutzerDto konvertiereZuBenutzerDto(final Benutzer benutzer)
	{
		return new BenutzerDto(
			benutzer.getPrimaerschluessel().getId().toString(),
			benutzer.getVorname(),
			benutzer.getNachname(),
			benutzer.getLebensalter(),
			benutzer.getKraftlevel().name(),
			benutzer.getGeschlecht().name(),
			benutzer.getErfahrung().name(),
			benutzer.getErnaehrung().name(),
			benutzer.getSchlafqualitaet().name(),
			benutzer.getStress().name(),
			benutzer.getDoping().name(),
			benutzer.getRegenerationsfaehigkeit().name(),
			konvertiereZuAuthentifizierungDto(benutzer.getAuthentifizierung()));
	}

	public static List<UebungDto> konvertiereAlleZuUebungDto(final List<Uebung> uebungen)
	{
		return uebungen
			.stream()
			.map(Konvertierer::konvertiereZuUebungDto)
			.collect(Collectors.toList());
	}

	public static UebungDto konvertiereZuUebungDto(final Uebung uebung)
	{
		return new UebungDto(
			uebung.getPrimaerschluessel().getId().toString(),
			uebung.getName(),
			uebung.getUebungsart().name(),
			uebung.getUebungskategorie().name(),
			konvertiereZuBelastungsfaktorDto(uebung.getBelastungsfaktor()));
	}

	public static List<BelastungsfaktorDto> konvertiereAlleZuBelastungsfaktorDto(
		final List<Belastungsfaktor> belastungsfaktoren)
	{
		return belastungsfaktoren
			.stream()
			.map(Konvertierer::konvertiereZuBelastungsfaktorDto)
			.collect(Collectors.toList());
	}

	public static BelastungsfaktorDto konvertiereZuBelastungsfaktorDto(final Belastungsfaktor belastungsfaktor)
	{
		return new BelastungsfaktorDto(
			belastungsfaktor.getPrimaerschluessel().getId().toString(),
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

	public static List<KraftwertDto> konvertiereAlleZuKraftwertDto(final List<Kraftwert> kraftwerte)
	{
		return kraftwerte
			.stream()
			.map(Konvertierer::konvertiereZuKraftwertDto)
			.collect(Collectors.toList());
	}

	public static KraftwertDto konvertiereZuKraftwertDto(final Kraftwert kraftwert)
	{
		return new KraftwertDto(
			kraftwert.getPrimaerschluessel().getId().toString(),
			kraftwert.getMaximum(),
			kraftwert.getKoerpergewicht(),
			kraftwert.getDatum().format(DateTimeFormatter.ofPattern(DATUMSFORMAT)),
			kraftwert.getWiederholungen().name());
	}

	public static Belastungsfaktor konvertiereZuBelastungsfaktor(final BelastungsfaktorDto belastungsfaktorDto)
	{
		return new Belastungsfaktor(
			new Primaerschluessel(belastungsfaktorDto.getPrimaerschluessel()),
			belastungsfaktorDto.getSquat(),
			belastungsfaktorDto.getBenchpress(),
			belastungsfaktorDto.getDeadlift(),
			belastungsfaktorDto.getTriceps(),
			belastungsfaktorDto.getChest(),
			belastungsfaktorDto.getCore(),
			belastungsfaktorDto.getBack(),
			belastungsfaktorDto.getBiceps(),
			belastungsfaktorDto.getGlutes(),
			belastungsfaktorDto.getQuads(),
			belastungsfaktorDto.getHamstrings(),
			belastungsfaktorDto.getShoulder());
	}
}
