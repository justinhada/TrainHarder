package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Kraftlevel;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;

public class VolumenrechnerSollte
{
	private Volumenrechner sut;

	@BeforeEach
	public void setup()
	{
		sut = new Volumenrechner();
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn Benutzer null ist")
	public void test01()
	{
		final var e = assertThrows(NullPointerException.class, () -> sut.berechneAnpassungsfaktor(null));

		assertThat(e.getMessage())
			.isEqualTo("Der Benutzer, für den der Anpassungsfaktor berechnet werden soll, existiert nicht!");
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor für Justin berechnen")
	public void test02()
	{
		final var benutzer = new Benutzer();
		benutzer.setVorname("Justin");
		benutzer.setNachname("Harder");
		benutzer.setKoerpergewicht(90);
		benutzer.setKoerpergroesse(178);
		benutzer.setLebensalter(20);
		benutzer.setGeschlecht(Geschlecht.MAENNLICH);
		benutzer.setErfahrung(Erfahrung.BEGINNER);
		benutzer.setErnaehrung(Ernaehrung.GUT);
		benutzer.setSchlafqualitaet(Schlafqualitaet.GUT);
		benutzer.setStress(Stress.MITTELMAESSIG);
		benutzer.setDoping(Doping.NEIN);
		benutzer.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);
		benutzer.setKraftlevel(Kraftlevel.CLASS_5);

		assertThat(sut.berechneAnpassungsfaktor(benutzer)).isEqualTo(9);
	}

	@Test
	@DisplayName("den richtigen Anpassungsfaktor für eine Frau berechnen")
	public void test03()
	{
		final var benutzer = new Benutzer();
		benutzer.setVorname("M.");
		benutzer.setNachname("Musterfrau");
		benutzer.setKoerpergewicht(90);
		benutzer.setKoerpergroesse(180);
		benutzer.setLebensalter(43);
		benutzer.setGeschlecht(Geschlecht.WEIBLICH);
		benutzer.setErfahrung(Erfahrung.BEGINNER);
		benutzer.setErnaehrung(Ernaehrung.GUT);
		benutzer.setSchlafqualitaet(Schlafqualitaet.GUT);
		benutzer.setStress(Stress.MITTELMAESSIG);
		benutzer.setDoping(Doping.NEIN);
		benutzer.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);
		benutzer.setKraftlevel(Kraftlevel.CLASS_3);

		assertThat(sut.berechneAnpassungsfaktor(benutzer)).isEqualTo(1);
	}
}
