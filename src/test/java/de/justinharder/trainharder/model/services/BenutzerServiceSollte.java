package de.justinharder.trainharder.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.repository.AuthentifizierungRepository;
import de.justinharder.trainharder.model.repository.BenutzerRepository;
import de.justinharder.trainharder.model.services.mapper.BenutzerDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.BenutzerDto;

public class BenutzerServiceSollte
{
	private BenutzerService sut;

	private BenutzerRepository benutzerRepository;
	private AuthentifizierungRepository authentifizierungRepository;
	private BenutzerDtoMapper benutzerDtoMapper;

	@BeforeEach
	public void setup()
	{
		benutzerRepository = mock(BenutzerRepository.class);
		authentifizierungRepository = mock(AuthentifizierungRepository.class);
		benutzerDtoMapper = mock(BenutzerDtoMapper.class);
		sut = new BenutzerService(benutzerRepository, authentifizierungRepository, benutzerDtoMapper);
	}

	private void angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(final Optional<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(benutzer);
	}

	private void angenommenDasBenutzerRepositoryGibtKeinenBenutzerZurueck()
	{
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(Optional.empty());
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(
		final Optional<Authentifizierung> authentifizierung)
	{
		when(authentifizierungRepository.ermittleZuId(any(Primaerschluessel.class))).thenReturn(authentifizierung);
	}

	private void angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierung()
	{
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(Optional.empty());
	}

	private void angenommenDasBenutzerRepositorySpeichertBenutzer(final Benutzer benutzer)
	{
		when(benutzerRepository.speichereBenutzer(any(Benutzer.class))).thenReturn(benutzer);
	}

	private void angenommenDerBenutzerDtoMapperGibtBenutzerDtoZurueck(final Benutzer benutzer,
		final BenutzerDto benutzerDto)
	{
		when(benutzerDtoMapper.konvertiere(benutzer)).thenReturn(benutzerDto);
	}

	private void angenommenDasBenutzerRepositoryGibtBenutzerZuAuthentifizierungZurueck(final String authentifizierungId,
		final Optional<Benutzer> benutzer)
	{
		when(benutzerRepository.ermittleZuAuthentifizierung(new Primaerschluessel(authentifizierungId)))
			.thenReturn(benutzer);
	}

	private void angenommenDasBenutzerRepositoryGibtKeinenBenutzerZurAuthentifizierungZurueck(
		final String authentifizierungId)
	{
		angenommenDasBenutzerRepositoryGibtBenutzerZuAuthentifizierungZurueck(authentifizierungId, Optional.empty());
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn kein Benutzer zu ID ermittelt wird")
	public void test01()
	{
		angenommenDasBenutzerRepositoryGibtKeinenBenutzerZurueck();

		final var id = new Primaerschluessel().getId().toString();
		final var exception = assertThrows(BenutzerNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo("Der Benutzer mit der ID \"" + id + "\" existiert nicht!");
	}

	@Test
	@DisplayName("einen Benutzer zu ID ermitteln")
	public void test02() throws BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.BENUTZER_DTO_JUSTIN;
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		angenommenDasBenutzerRepositoryGibtEinenBenutzerZurueck(Optional.of(benutzer));
		angenommenDerBenutzerDtoMapperGibtBenutzerDtoZurueck(benutzer, erwartet);

		final var id = new Primaerschluessel().getId().toString();
		final var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BenutzerNichtGefundenException werfen, wenn kein Benutzer zur Authentifizierung ermittelt werden kann")
	public void test03() throws BenutzerNichtGefundenException
	{
		final var authentifizierungId = new Primaerschluessel().getId().toString();
		final var erwartet =
			"Der Benutzer mit der AuthentifizierungID \"" + authentifizierungId + "\" existiert nicht!";
		angenommenDasBenutzerRepositoryGibtKeinenBenutzerZurAuthentifizierungZurueck(authentifizierungId);

		final var exception = assertThrows(BenutzerNichtGefundenException.class,
			() -> sut.ermittleZuAuthentifizierung(authentifizierungId));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer zur AuthentifizierungID ermitteln")
	public void test04() throws BenutzerNichtGefundenException
	{
		final var erwartet = Testdaten.BENUTZER_DTO_JUSTIN;
		final var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		angenommenDasBenutzerRepositoryGibtBenutzerZuAuthentifizierungZurueck(authentifizierungId,
			Optional.of(benutzer));
		angenommenDerBenutzerDtoMapperGibtBenutzerDtoZurueck(benutzer, erwartet);

		final var ergebnis = sut.ermittleZuAuthentifizierung(authentifizierungId);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("AuthentifizierungNichtGefundenException werfen, wenn Authentifizierung nicht gefunden werden kann")
	public void test05()
	{
		final var authentifizierungId = new Primaerschluessel().getId().toString();
		final var erwartet = "Die Authentifizierung mit der ID \"" + authentifizierungId + "\" existiert nicht!";
		angenommenDasAuthentifizierungRepositoryErmitteltKeineAuthentifizierung();

		final var exception = assertThrows(AuthentifizierungNichtGefundenException.class,
			() -> sut.speichereBenutzer(Testdaten.BENUTZER_DTO_JUSTIN, authentifizierungId));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("einen Benutzer erstellen")
	public void test06() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = Testdaten.BENUTZER_DTO_JUSTIN;
		final var benutzer = Testdaten.BENUTZER_JUSTIN;
		final var authentifizierung = Testdaten.AUTHENTIFIZIERUNG_JUSTIN;
		angenommenDasAuthentifizierungRepositoryErmitteltAuthentifizierungZuId(Optional.of(authentifizierung));
		angenommenDasBenutzerRepositorySpeichertBenutzer(benutzer);
		angenommenDerBenutzerDtoMapperGibtBenutzerDtoZurueck(benutzer, erwartet);

		final var authentifizierungId = Testdaten.AUTHENTIFIZIERUNG_JUSTIN_ID.getId().toString();
		final var ergebnis = sut.speichereBenutzer(erwartet, authentifizierungId);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
