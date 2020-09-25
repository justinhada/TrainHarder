package de.justinharder.trainharder.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.model.repository.UebungRepository;
import de.justinharder.trainharder.model.services.mapper.UebungDtoMapper;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.UebungDto;

class UebungServiceSollte
{
	private UebungService sut;

	private UebungRepository uebungRepository;
	private BelastungsfaktorRepository belastungsfaktorRepository;
	private UebungDtoMapper uebungDtoMapper;

	@BeforeEach
	void setup()
	{
		uebungRepository = mock(UebungRepository.class);
		belastungsfaktorRepository = mock(BelastungsfaktorRepository.class);
		uebungDtoMapper = mock(UebungDtoMapper.class);

		sut = new UebungService(uebungRepository, belastungsfaktorRepository, uebungDtoMapper);
	}

	private void angenommenDasUebungRepositoryGibtAlleUebungenZurueck(final List<Uebung> uebungen)
	{
		when(uebungRepository.ermittleAlle()).thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtAlleUebungenZuUebungsartZurueck(final String uebungsart,
		final List<Uebung> uebungen)
	{
		when(uebungRepository.ermittleAlleZuUebungsart(Uebungsart.fromString(uebungsart)))
			.thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtAlleUebungenZuUebungskategorieZurueck(final String uebungskategorie,
		final List<Uebung> uebungen)
	{
		when(uebungRepository
			.ermittleAlleZuUebungskategorie(Uebungskategorie.fromString(uebungskategorie)))
				.thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtEineUebungZurueck(final String id, final Optional<Uebung> uebung)
	{
		when(uebungRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(uebung);
	}

	private void angenommenDasUebungRepositoryGibtNullZurueck(final String id)
	{
		angenommenDasUebungRepositoryGibtEineUebungZurueck(id, Optional.empty());
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(
		final String belastungsfaktorId,
		final Optional<Belastungsfaktor> belastungsfaktor)
	{
		when(belastungsfaktorRepository.ermittleZuId(new Primaerschluessel(belastungsfaktorId)))
			.thenReturn(belastungsfaktor);
	}

	private void angenommenDasBelastungsfaktorRepositoryErmitteltKeinenBelastungsfaktor(final String belastungsfaktorId)
	{
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(belastungsfaktorId, Optional.empty());
	}

	private void angenommenDasUebungRepositorySpeichertUebung(final Uebung uebung)
	{
		when(uebungRepository.speichereUebung(any(Uebung.class))).thenReturn(uebung);
	}

	private void angenommenDerUebungDtoMapperKonvertiertAlleUebungDtos(final List<Uebung> uebungen,
		final List<UebungDto> uebungDtos)
	{
		when(uebungDtoMapper.konvertiereAlle(uebungen)).thenReturn(uebungDtos);
	}

	private void angenommenDerUebungDtoMapperKonvertiertUebungDto(final Uebung uebung, final UebungDto uebungDto)
	{
		when(uebungDtoMapper.konvertiere(uebung)).thenReturn(uebungDto);
	}

	@Test
	@DisplayName("alle Uebungen ermitteln")
	void test01()
	{
		final var erwartet = List.of(
			Testdaten.UEBUNG_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN);
		final var uebungen = List.of(
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN);
		angenommenDasUebungRepositoryGibtAlleUebungenZurueck(uebungen);
		angenommenDerUebungDtoMapperKonvertiertAlleUebungDtos(uebungen, erwartet);

		final var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(uebungRepository).ermittleAlle();
		verify(uebungDtoMapper).konvertiereAlle(uebungen);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Uebungsart null ist")
	void test02()
	{
		final var erwartet = "Die Ermittlung der Uebungen benötigt eine gültige Uebungsart!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuUebungsart(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("alle Uebungen zu Uebungsart ermitteln")
	void test03()
	{
		final var erwartet = List.of(
			Testdaten.UEBUNG_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN);
		final var uebungen = List.of(
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN);
		final var uebungsart = "GRUNDUEBUNG";
		angenommenDasUebungRepositoryGibtAlleUebungenZuUebungsartZurueck(uebungsart, uebungen);
		angenommenDerUebungDtoMapperKonvertiertAlleUebungDtos(uebungen, erwartet);

		final var ergebnis = sut.ermittleZuUebungsart(uebungsart);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(uebungRepository).ermittleAlleZuUebungsart(Uebungsart.fromString(uebungsart));
		verify(uebungDtoMapper).konvertiereAlle(uebungen);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Uebungskategorie null ist")
	void test04()
	{
		final var erwartet = "Die Ermittlung der Uebungen benötigt eine gültige Uebungskategorie!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuUebungskategorie(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("alle Uebungen zu Uebungskategorie ermitteln")
	void test05()
	{
		final var erwartet = List.of(Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE);
		final var uebungen = List.of(Testdaten.UEBUNG_LOWBAR_KNIEBEUGE);
		final var uebungskategorie = "WETTKAMPF_KNIEBEUGE";
		angenommenDasUebungRepositoryGibtAlleUebungenZuUebungskategorieZurueck(uebungskategorie, uebungen);
		angenommenDerUebungDtoMapperKonvertiertAlleUebungDtos(uebungen, erwartet);

		final var ergebnis = sut.ermittleZuUebungskategorie(uebungskategorie);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(uebungRepository)
			.ermittleAlleZuUebungskategorie(Uebungskategorie.fromString(uebungskategorie));
		verify(uebungDtoMapper).konvertiereAlle(uebungen);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die UebungID null ist")
	void test06()
	{
		final var erwartet = "Die Ermittlung der Uebung benötigt eine gültige UebungID!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("UebungNichtGefundenException werfen, wenn die UebungID nicht existiert")
	void test07()
	{
		final var id = new Primaerschluessel().getId().toString();
		final var erwartet = "Die Uebung mit der ID \"" + id + "\" existiert nicht!";
		angenommenDasUebungRepositoryGibtNullZurueck(id);

		final var exception = assertThrows(UebungNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("eine Uebung zur ID ermitteln")
	void test08() throws UebungNichtGefundenException
	{
		final var erwartet = Testdaten.UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN;
		final var uebung = Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN;
		final var id = Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN_ID.getId().toString();
		angenommenDasUebungRepositoryGibtEineUebungZurueck(id, Optional.of(uebung));
		angenommenDerUebungDtoMapperKonvertiertUebungDto(uebung, erwartet);

		final var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(id));
		verify(uebungDtoMapper).konvertiere(uebung);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das UebungDto null ist")
	void test09()
	{
		final var erwartet = "Zur Erstellung der Uebung wird ein gültiges UebungDto benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.speichereUebung(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die BelastungsfaktorID null ist")
	void test10()
	{
		final var erwartet = "Zur Erstellung der Uebung wird ein gültiges UebungDto benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.speichereUebung(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BelastungsfaktorNichtGefundenException werfen, wenn die BelastungsfaktorID nicht existiert")
	void test11()
	{
		final var belastungsfaktorId = new Primaerschluessel().getId().toString();
		final var erwartet = "Der Belastungsfaktor mit der ID \"" + belastungsfaktorId + "\" existiert nicht!";
		angenommenDasBelastungsfaktorRepositoryErmitteltKeinenBelastungsfaktor(belastungsfaktorId);

		final var exception = assertThrows(BelastungsfaktorNichtGefundenException.class,
			() -> sut.speichereUebung(Testdaten.UEBUNG_DTO_WETTKAMPFBANKDRUECKEN, belastungsfaktorId));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(belastungsfaktorRepository).ermittleZuId(new Primaerschluessel(belastungsfaktorId));
	}

	@Test
	@DisplayName("eine Uebung erstellen")
	void test12() throws BelastungsfaktorNichtGefundenException
	{
		final var erwartet = Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE;
		final var uebung = Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN;
		final var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE;
		final var belastungsfaktorId = Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID.getId().toString();
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(
			belastungsfaktorId,
			Optional.of(belastungsfaktor));
		angenommenDasUebungRepositorySpeichertUebung(uebung);
		angenommenDerUebungDtoMapperKonvertiertUebungDto(uebung, erwartet);

		final var ergebnis = sut.speichereUebung(erwartet, belastungsfaktorId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(belastungsfaktorRepository).ermittleZuId(new Primaerschluessel(belastungsfaktorId));
		verify(uebungDtoMapper).konvertiere(uebung);
	}
}
