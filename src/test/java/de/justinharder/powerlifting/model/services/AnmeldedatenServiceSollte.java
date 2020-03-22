package de.justinharder.powerlifting.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.model.domain.Anmeldedaten;
import de.justinharder.powerlifting.model.domain.exceptions.AnmeldedatenNichtGefundenException;
import de.justinharder.powerlifting.model.repository.AnmeldedatenRepository;
import de.justinharder.powerlifting.setup.Testdaten;

public class AnmeldedatenServiceSollte
{
	private AnmeldedatenService sut;
	private AnmeldedatenRepository anmeldedatenRepository;

	@BeforeEach
	public void setup()
	{
		anmeldedatenRepository = mock(AnmeldedatenRepository.class);
		sut = new AnmeldedatenService(anmeldedatenRepository);
	}

	private void angenommenDasAnmeldedatenRepositoryGibtAlleAnmeldedatenZurueck(
		final List<Anmeldedaten> alleAnmeldedaten)
	{
		when(anmeldedatenRepository.ermittleAlle()).thenReturn(alleAnmeldedaten);
	}

	private void angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZurueck(final Anmeldedaten anmeldedaten)
	{
		when(anmeldedatenRepository.ermittleZuId(anyInt())).thenReturn(anmeldedaten);
	}

	private void angenommenDasAnmeldedatenRepositoryGibtNullZurueck()
	{
		angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZurueck(null);
	}

	private void angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZuBenutzerZurueck(final Anmeldedaten anmeldedaten)
	{
		when(anmeldedatenRepository.ermittleZuBenutzer(anyInt())).thenReturn(anmeldedaten);
	}

	private void angenommenDasAnmeldedatenRepositoryGibtNullZuBenutzerIdZurueck()
	{
		angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZuBenutzerZurueck(null);
	}

	@Test
	@DisplayName("alle Anmeldedaten ermitteln")
	public void test01()
	{
		final var erwartet = List.of(Testdaten.ANMELDEDATENEINTRAG_JUSTIN, Testdaten.ANMELDEDATENEINTRAG_EDUARD);
		final var alleAnmeldedaten = List.of(Testdaten.ANMELDEDATEN_JUSTIN, Testdaten.ANMELDEDATEN_EDUARD);
		angenommenDasAnmeldedatenRepositoryGibtAlleAnmeldedatenZurueck(alleAnmeldedaten);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AnmeldedatenNichtGefundenException werfen, wenn ID zu keinen Anmeldedaten gehört")
	public void test02()
	{
		angenommenDasAnmeldedatenRepositoryGibtNullZurueck();

		final var exception = assertThrows(AnmeldedatenNichtGefundenException.class, () -> sut.ermittleZuId("10000"));

		assertThat(exception.getMessage()).isEqualTo("Die Anmeldedaten mit der ID \"10000\" existieren nicht!");
	}

	@Test
	@DisplayName("Anmeldedaten zur ID ermitteln")
	public void test03() throws AnmeldedatenNichtGefundenException
	{
		final var erwartet = Testdaten.ANMELDEDATENEINTRAG_EDUARD;
		final var anmeldedaten = Testdaten.ANMELDEDATEN_EDUARD;
		angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZurueck(anmeldedaten);

		final var ergebnis = sut.ermittleZuId("0");

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AnmeldedatenNichtGefundenException werfen, wenn BenutzerID zu keinen Anmeldedaten gehört")
	public void test04()
	{
		angenommenDasAnmeldedatenRepositoryGibtNullZuBenutzerIdZurueck();

		final var exception =
			assertThrows(AnmeldedatenNichtGefundenException.class, () -> sut.ermittleZuBenutzer("10000"));

		assertThat(exception.getMessage()).isEqualTo("Die Anmeldedaten mit der BenutzerID \"10000\" existieren nicht!");
	}

	@Test
	@DisplayName("Anmeldedaten zu Benutzer ermitteln")
	public void test05() throws AnmeldedatenNichtGefundenException
	{
		final var erwartet = Testdaten.ANMELDEDATENEINTRAG_JUSTIN;
		final var anmeldedaten = Testdaten.ANMELDEDATEN_JUSTIN;
		angenommenDasAnmeldedatenRepositoryGibtAnmeldedatenZuBenutzerZurueck(anmeldedaten);

		final var ergebnis = sut.ermittleZuBenutzer(String.valueOf(Testdaten.BENUTZER_JUSTIN.getId()));

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("Anmeldedaten erstellen")
	public void test06()
	{
		final var anmeldedatenEintrag = Testdaten.ANMELDEDATENEINTRAG_JUSTIN;
		final var anmeldedaten = Testdaten.ANMELDEDATEN_JUSTIN;

		sut.erstelleAnmeldedaten(anmeldedatenEintrag);

		verify(anmeldedatenRepository).erstelleAnmeldedaten(anmeldedaten);
	}
}
