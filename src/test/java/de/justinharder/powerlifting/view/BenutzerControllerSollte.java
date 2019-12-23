package de.justinharder.powerlifting.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.dto.BenutzerEintrag;
import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Kraftlevel;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;
import de.justinharder.powerlifting.model.services.BenutzerService;

public class BenutzerControllerSollte
{
	private static final BenutzerEintrag JUSTIN_BENUTZEREINTRAG =
		new BenutzerEintrag(1, "Justin", "Harder", 90, 178, 21, Kraftlevel.CLASS_5, Geschlecht.MAENNLICH,
			Erfahrung.BEGINNER, Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG, Doping.NEIN,
			Regenerationsfaehigkeit.GUT);
	private static final BenutzerEintrag FRAU_BENUTZEREINTRAG =
		new BenutzerEintrag(2, "M.", "Musterfrau", 90, 180, 43, Kraftlevel.CLASS_3, Geschlecht.WEIBLICH,
			Erfahrung.BEGINNER, Ernaehrung.GUT, Schlafqualitaet.GUT, Stress.MITTELMAESSIG, Doping.NEIN,
			Regenerationsfaehigkeit.GUT);

	private BenutzerController sut;
	private BenutzerService benutzerService;

	@BeforeEach
	public void setup()
	{
		benutzerService = mock(BenutzerService.class);
		sut = new BenutzerController(benutzerService);
	}

	private void angenommenDerBenutzerServiceGibtAlleBenutzerZurueck(final List<BenutzerEintrag> erwartet)
	{
		when(benutzerService.ermittleAlle()).thenReturn(erwartet);
	}

	@Test
	@DisplayName("eine Liste aller BenutzerEinträge zurückgeben")
	public void test01()
	{
		final var erwartet = List.of(JUSTIN_BENUTZEREINTRAG, FRAU_BENUTZEREINTRAG);
		angenommenDerBenutzerServiceGibtAlleBenutzerZurueck(erwartet);

		final var ergebnis = sut.getBenutzer();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

}
