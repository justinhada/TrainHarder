package de.justinharder.trainharder.model.services;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

	private void angenommenDasUebungRepositoryGibtAlleUebungenZurueck(List<Uebung> uebungen)
	{
		when(uebungRepository.ermittleAlle()).thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtAlleUebungenZuUebungsartZurueck(String uebungsart,
		List<Uebung> uebungen)
	{
		when(uebungRepository.ermittleAlleZuUebungsart(Uebungsart.zuWert(uebungsart)))
			.thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtAlleUebungenZuUebungskategorieZurueck(String uebungskategorie,
		List<Uebung> uebungen)
	{
		when(uebungRepository.ermittleAlleZuUebungskategorie(Uebungskategorie.zuWert(uebungskategorie)))
			.thenReturn(uebungen);
	}

	private void angenommenDasUebungRepositoryGibtEineUebungZurueck(String id, Optional<Uebung> uebung)
	{
		when(uebungRepository.ermittleZuId(new Primaerschluessel(id))).thenReturn(uebung);
	}

	private void angenommenDasUebungRepositoryGibtNullZurueck(String id)
	{
		angenommenDasUebungRepositoryGibtEineUebungZurueck(id, Optional.empty());
	}

	private void angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(String belastungsfaktorId,
		Optional<Belastungsfaktor> belastungsfaktor)
	{
		when(belastungsfaktorRepository.ermittleZuId(new Primaerschluessel(belastungsfaktorId)))
			.thenReturn(belastungsfaktor);
	}

	private void angenommenDasBelastungsfaktorRepositoryErmitteltKeinenBelastungsfaktor(String belastungsfaktorId)
	{
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(belastungsfaktorId, Optional.empty());
	}

	private void angenommenDasUebungRepositorySpeichertUebung(Uebung uebung)
	{
		when(uebungRepository.speichereUebung(any(Uebung.class))).thenReturn(uebung);
	}

	private void angenommenDerUebungDtoMapperMapptAlleUebungDtos(List<Uebung> uebungen, List<UebungDto> uebungDtos)
	{
		when(uebungDtoMapper.mappeAlle(uebungen)).thenReturn(uebungDtos);
	}

	private void angenommenDerUebungDtoMapperMapptUebungDto(Uebung uebung, UebungDto uebungDto)
	{
		when(uebungDtoMapper.mappe(uebung)).thenReturn(uebungDto);
	}

	@Test
	@DisplayName("alle Uebungen ermitteln")
	void test01()
	{
		var erwartet = List.of(
			Testdaten.UEBUNG_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN);
		var uebungen = List.of(
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN);
		angenommenDasUebungRepositoryGibtAlleUebungenZurueck(uebungen);
		angenommenDerUebungDtoMapperMapptAlleUebungDtos(uebungen, erwartet);

		var ergebnis = sut.ermittleAlle();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(uebungRepository).ermittleAlle();
		verify(uebungDtoMapper).mappeAlle(uebungen);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Uebungsart null ist")
	void test02()
	{
		var erwartet = "Die Ermittlung der Uebungen benötigt eine gültige Uebungsart!";

		var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuUebungsart(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("alle Uebungen zu Uebungsart ermitteln")
	void test03()
	{
		var erwartet = List.of(
			Testdaten.UEBUNG_DTO_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN);
		var uebungen = List.of(
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN);
		var uebungsart = "GRUNDUEBUNG";
		angenommenDasUebungRepositoryGibtAlleUebungenZuUebungsartZurueck(uebungsart, uebungen);
		angenommenDerUebungDtoMapperMapptAlleUebungDtos(uebungen, erwartet);

		var ergebnis = sut.ermittleZuUebungsart(uebungsart);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(uebungRepository).ermittleAlleZuUebungsart(Uebungsart.zuWert(uebungsart));
		verify(uebungDtoMapper).mappeAlle(uebungen);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Uebungskategorie null ist")
	void test04()
	{
		var erwartet = "Die Ermittlung der Uebungen benötigt eine gültige Uebungskategorie!";

		var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuUebungskategorie(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("alle Uebungen zu Uebungskategorie ermitteln")
	void test05()
	{
		var erwartet = List.of(Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE);
		var uebungen = List.of(Testdaten.UEBUNG_LOWBAR_KNIEBEUGE);
		var uebungskategorie = "WETTKAMPF_KNIEBEUGE";
		angenommenDasUebungRepositoryGibtAlleUebungenZuUebungskategorieZurueck(uebungskategorie, uebungen);
		angenommenDerUebungDtoMapperMapptAlleUebungDtos(uebungen, erwartet);

		var ergebnis = sut.ermittleZuUebungskategorie(uebungskategorie);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(uebungRepository).ermittleAlleZuUebungskategorie(Uebungskategorie.zuWert(uebungskategorie));
		verify(uebungDtoMapper).mappeAlle(uebungen);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die UebungID null ist")
	void test06()
	{
		var erwartet = "Die Ermittlung der Uebung benötigt eine gültige UebungID!";

		var exception = assertThrows(NullPointerException.class, () -> sut.ermittleZuId(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("UebungNichtGefundenException werfen, wenn die UebungID nicht existiert")
	void test07()
	{
		var id = new Primaerschluessel().getId().toString();
		var erwartet = "Die Uebung mit der ID \"" + id + "\" existiert nicht!";
		angenommenDasUebungRepositoryGibtNullZurueck(id);

		var exception = assertThrows(UebungNichtGefundenException.class, () -> sut.ermittleZuId(id));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(id));
	}

	@Test
	@DisplayName("eine Uebung zur ID ermitteln")
	void test08() throws UebungNichtGefundenException
	{
		var erwartet = Testdaten.UEBUNG_DTO_KONVENTIONELLES_KREUZHEBEN;
		var uebung = Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN;
		var id = Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN_ID.getId().toString();
		angenommenDasUebungRepositoryGibtEineUebungZurueck(id, Optional.of(uebung));
		angenommenDerUebungDtoMapperMapptUebungDto(uebung, erwartet);

		var ergebnis = sut.ermittleZuId(id);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(uebungRepository).ermittleZuId(new Primaerschluessel(id));
		verify(uebungDtoMapper).mappe(uebung);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das UebungDto null ist")
	void test09()
	{
		var erwartet = "Zur Erstellung der Uebung wird ein gültiges UebungDto benötigt!";

		var exception = assertThrows(NullPointerException.class, () -> sut.speichereUebung(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die BelastungsfaktorID null ist")
	void test10()
	{
		var erwartet = "Zur Erstellung der Uebung wird ein gültiges UebungDto benötigt!";

		var exception = assertThrows(NullPointerException.class, () -> sut.speichereUebung(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("BelastungsfaktorNichtGefundenException werfen, wenn die BelastungsfaktorID nicht existiert")
	void test11()
	{
		var belastungsfaktorId = new Primaerschluessel().getId().toString();
		var erwartet = "Der Belastungsfaktor mit der ID \"" + belastungsfaktorId + "\" existiert nicht!";
		angenommenDasBelastungsfaktorRepositoryErmitteltKeinenBelastungsfaktor(belastungsfaktorId);

		var exception = assertThrows(BelastungsfaktorNichtGefundenException.class,
			() -> sut.speichereUebung(Testdaten.UEBUNG_DTO_WETTKAMPFBANKDRUECKEN, belastungsfaktorId));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
		verify(belastungsfaktorRepository).ermittleZuId(new Primaerschluessel(belastungsfaktorId));
	}

	@Test
	@DisplayName("eine Uebung erstellen")
	void test12() throws BelastungsfaktorNichtGefundenException
	{
		var erwartet = Testdaten.UEBUNG_DTO_LOWBAR_KNIEBEUGE;
		var uebung = Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN;
		var belastungsfaktor = Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE;
		var belastungsfaktorId = Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE_ID.getId().toString();
		angenommenDasBelastungsfaktorRepositoryGibtEinenBelastungsfaktorZurueck(belastungsfaktorId,
			Optional.of(belastungsfaktor));
		angenommenDasUebungRepositorySpeichertUebung(uebung);
		angenommenDerUebungDtoMapperMapptUebungDto(uebung, erwartet);

		var ergebnis = sut.speichereUebung(erwartet, belastungsfaktorId);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(belastungsfaktorRepository).ermittleZuId(new Primaerschluessel(belastungsfaktorId));
		verify(uebungDtoMapper).mappe(uebung);
	}
}
