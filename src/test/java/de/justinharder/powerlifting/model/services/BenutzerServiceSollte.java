package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
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
	private static final HashMap<Integer, Kraftwert> kraftwerte = new HashMap<>();

	private static final BenutzerEintrag JUSTIN_BENUTZEREINTRAG =
		new BenutzerEintrag(1, "Justin", "Harder", 90, 178, 21, Kraftlevel.CLASS_5, Geschlecht.MAENNLICH,
			Erfahrung.BEGINNER, Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG, Doping.NEIN,
			Regenerationsfaehigkeit.GUT);
	private static final BenutzerEintrag FRAU_BENUTZEREINTRAG =
		new BenutzerEintrag(2, "M.", "Musterfrau", 90, 180, 43, Kraftlevel.CLASS_3, Geschlecht.WEIBLICH,
			Erfahrung.BEGINNER, Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG, Doping.NEIN,
			Regenerationsfaehigkeit.GUT);
	private Benutzer justinBenutzer;
	private Benutzer frauBenutzer;

	private BenutzerService sut;
	private BenutzerRepository benutzerRepository;

	@BeforeEach
	public void setup()
	{
		kraftwerte.put(1, new Kraftwert(1, new Uebung(), 100));
		kraftwerte.put(2, new Kraftwert(2, new Uebung(), 100));
		kraftwerte.put(3, new Kraftwert(3, new Uebung(), 100));
		justinBenutzer =
			new Benutzer(1, "Justin", "Harder", 90, 178, 21, kraftwerte, Kraftlevel.CLASS_5,
				Geschlecht.MAENNLICH, Erfahrung.BEGINNER, Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG,
				Doping.NEIN, Regenerationsfaehigkeit.GUT);
		frauBenutzer =
			new Benutzer(2, "M.", "Musterfrau", 90, 180, 43, kraftwerte, Kraftlevel.CLASS_3, Geschlecht.WEIBLICH,
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
}
