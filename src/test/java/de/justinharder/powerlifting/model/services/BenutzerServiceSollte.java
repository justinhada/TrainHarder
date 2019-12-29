package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwert;
import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.dto.BenutzerEintrag;
import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Kraftlevel;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;
import de.justinharder.powerlifting.model.repository.BenutzerRepository;

public class BenutzerServiceSollte
{
	private static final HashMap<Integer, Kraftwert> kraftwerteJustin = new HashMap<>();
	private static final HashMap<Integer, Kraftwert> kraftwerteFrau = new HashMap<>();

	private static final BenutzerEintrag JUSTIN_BENUTZEREINTRAG =
		new BenutzerEintrag("Justin", "Harder", 90, 178, 21, Kraftlevel.CLASS_5, Geschlecht.MAENNLICH,
			Erfahrung.BEGINNER, Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG, Doping.NEIN,
			Regenerationsfaehigkeit.GUT);
	private static final BenutzerEintrag FRAU_BENUTZEREINTRAG =
		new BenutzerEintrag("M.", "Musterfrau", 90, 180, 43, Kraftlevel.CLASS_3, Geschlecht.WEIBLICH,
			Erfahrung.BEGINNER, Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG, Doping.NEIN,
			Regenerationsfaehigkeit.GUT);
	private Benutzer justinBenutzer;
	private Benutzer frauBenutzer;

	private BenutzerService sut;
	private BenutzerRepository benutzerRepository;

	@BeforeEach
	public void setup()
	{
		kraftwerteJustin.put(1, new Kraftwert(1, new Uebung(), 100, justinBenutzer));
		kraftwerteJustin.put(2, new Kraftwert(2, new Uebung(), 100, justinBenutzer));
		kraftwerteJustin.put(3, new Kraftwert(3, new Uebung(), 100, justinBenutzer));
		kraftwerteFrau.put(1, new Kraftwert(1, new Uebung(), 100, frauBenutzer));
		kraftwerteFrau.put(2, new Kraftwert(2, new Uebung(), 100, frauBenutzer));
		kraftwerteFrau.put(3, new Kraftwert(3, new Uebung(), 100, frauBenutzer));
		justinBenutzer =
			new Benutzer(1, "Justin", "Harder", 90, 178, 21, kraftwerteJustin, Kraftlevel.CLASS_5,
				Geschlecht.MAENNLICH, Erfahrung.BEGINNER, Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG,
				Doping.NEIN, Regenerationsfaehigkeit.GUT);
		frauBenutzer =
			new Benutzer(2, "M.", "Musterfrau", 90, 180, 43, kraftwerteFrau, Kraftlevel.CLASS_3, Geschlecht.WEIBLICH,
				Erfahrung.BEGINNER, Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG, Doping.NEIN,
				Regenerationsfaehigkeit.GUT);

		benutzerRepository = mock(BenutzerRepository.class);
		sut = new BenutzerService(benutzerRepository);
	}

	private void angenommenDasBenutzerRepositoryGibtAlleBenutzerZurueck(final List<Benutzer> alleBenutzer)
	{
		when(benutzerRepository.ermittleAlle()).thenReturn(alleBenutzer);
	}

	@Test
	@DisplayName("alle Benutzer ermitteln")
	public void test01()
	{
		final var erwartet = List.of(JUSTIN_BENUTZEREINTRAG, FRAU_BENUTZEREINTRAG);
		final var alleBenutzer = List.of(justinBenutzer, frauBenutzer);
		angenommenDasBenutzerRepositoryGibtAlleBenutzerZurueck(alleBenutzer);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer erstellen")
	public void test02()
	{
		final var erwartet = new BenutzerEintrag("Herr", "Harder", 90, 178, 21, Kraftlevel.CLASS_5,
			Geschlecht.MAENNLICH, Erfahrung.BEGINNER, Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG,
			Doping.NEIN, Regenerationsfaehigkeit.GUT);
		final var eingabe =
			new Benutzer("Herr", "Harder", 90, 178, 21, Kraftlevel.CLASS_5, Geschlecht.MAENNLICH, Erfahrung.BEGINNER,
				Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG, Doping.NEIN, Regenerationsfaehigkeit.GUT);

		final var ergebnis = sut.erstelleBenutzer("Herr", "Harder", 90, 178, 21, "MAENNLICH", "BEGINNER", "GUT",
			"GUT", "MITTELMAESSIG", "NEIN", "GUT");

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(benutzerRepository).erstelleBenutzer(eingabe);
	}
}
