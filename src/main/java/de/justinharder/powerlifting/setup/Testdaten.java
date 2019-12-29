package de.justinharder.powerlifting.setup;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Kraftlevel;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;

public class Testdaten
{
	public static final Benutzer JUSTIN = new Benutzer();
	public static final Benutzer EDUARD = new Benutzer();
	public static final Benutzer ANNA = new Benutzer();
	public static final Benutzer ANETTE = new Benutzer();
	public static final Benutzer GOTT = new Benutzer();
	public static final Benutzer BABA = new Benutzer();

	static
	{
		JUSTIN.setVorname("Justin");
		JUSTIN.setNachname("Harder");
		JUSTIN.setKoerpergewicht(90);
		JUSTIN.setKoerpergroesse(178);
		JUSTIN.setLebensalter(21);
		JUSTIN.setKraftlevel(Kraftlevel.CLASS_5);
		JUSTIN.setGeschlecht(Geschlecht.MAENNLICH);
		JUSTIN.setErfahrung(Erfahrung.BEGINNER);
		JUSTIN.setErnaehrung(Ernaehrung.GUT);
		JUSTIN.setSchlafqualitaet(Schlafqualitaet.GUT);
		JUSTIN.setStress(Stress.MITTELMAESSIG);
		JUSTIN.setDoping(Doping.NEIN);
		JUSTIN.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);

		EDUARD.setVorname("Eduard");
		EDUARD.setNachname("Stremel");
		EDUARD.setKoerpergewicht(64);
		EDUARD.setKoerpergroesse(182);
		EDUARD.setLebensalter(14);
		EDUARD.setKraftlevel(Kraftlevel.CLASS_4);
		EDUARD.setGeschlecht(Geschlecht.MAENNLICH);
		EDUARD.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		EDUARD.setErnaehrung(Ernaehrung.SCHLECHT);
		EDUARD.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		EDUARD.setStress(Stress.NIEDRIG);
		EDUARD.setDoping(Doping.JA);
		EDUARD.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		ANNA.setVorname("Anna");
		ANNA.setNachname("Aufbau");
		ANNA.setKoerpergewicht(64);
		ANNA.setKoerpergroesse(182);
		ANNA.setLebensalter(14);
		ANNA.setKraftlevel(Kraftlevel.CLASS_2);
		ANNA.setGeschlecht(Geschlecht.WEIBLICH);
		ANNA.setErfahrung(Erfahrung.FORTGESCHRITTEN);
		ANNA.setErnaehrung(Ernaehrung.SCHLECHT);
		ANNA.setSchlafqualitaet(Schlafqualitaet.SCHLECHT);
		ANNA.setStress(Stress.NIEDRIG);
		ANNA.setDoping(Doping.JA);
		ANNA.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		ANETTE.setVorname("Anette");
		ANETTE.setNachname("Masseschwein");
		ANETTE.setKoerpergewicht(120);
		ANETTE.setKoerpergroesse(203);
		ANETTE.setLebensalter(43);
		ANETTE.setKraftlevel(Kraftlevel.CLASS_3);
		ANETTE.setGeschlecht(Geschlecht.WEIBLICH);
		ANETTE.setErfahrung(Erfahrung.EXPERTE);
		ANETTE.setErnaehrung(Ernaehrung.GUT);
		ANETTE.setSchlafqualitaet(Schlafqualitaet.DURCHSCHNITT);
		ANETTE.setStress(Stress.HOCH);
		ANETTE.setDoping(Doping.NEIN);
		ANETTE.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.UNTERDURCHSCHNITTLICH);

		GOTT.setVorname("Elite");
		GOTT.setNachname("sein Vater");
		GOTT.setKoerpergewicht(145);
		GOTT.setKoerpergroesse(190);
		GOTT.setLebensalter(32);
		GOTT.setKraftlevel(Kraftlevel.ELITE);
		GOTT.setGeschlecht(Geschlecht.MAENNLICH);
		GOTT.setErfahrung(Erfahrung.EXPERTE);
		GOTT.setErnaehrung(Ernaehrung.GUT);
		GOTT.setSchlafqualitaet(Schlafqualitaet.GUT);
		GOTT.setStress(Stress.NIEDRIG);
		GOTT.setDoping(Doping.JA);
		GOTT.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.PERFEKT);

		BABA.setVorname("Baba");
		BABA.setNachname("von Gewichten");
		BABA.setKoerpergewicht(110);
		BABA.setKoerpergroesse(160);
		BABA.setLebensalter(55);
		BABA.setKraftlevel(Kraftlevel.CLASS_1);
		BABA.setGeschlecht(Geschlecht.MAENNLICH);
		BABA.setErfahrung(Erfahrung.SEHR_FORTGESCHRITTEN);
		BABA.setErnaehrung(Ernaehrung.GUT);
		BABA.setSchlafqualitaet(Schlafqualitaet.GUT);
		BABA.setStress(Stress.NIEDRIG);
		BABA.setDoping(Doping.JA);
		BABA.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.SCHLECHT);
	}
}
