package de.justinharder.trainharder.model.domain;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import de.justinharder.trainharder.setup.Testdaten;

class UebungSollte
{
	private Uebung sut;

	@BeforeEach
	void setup()
	{
		sut = Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	void test01()
	{
		org.hamcrest.MatcherAssert.assertThat(Uebung.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor haben")
	void test02()
	{
		final var id = new Primaerschluessel();
		final var uebung = new Uebung(
			id,
			"Wettkampfbankdrücken (pausiert)",
			Uebungsart.GRUNDUEBUNG,
			Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
			Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);

		assertAll(
			() -> assertThat(uebung.getPrimaerschluessel()).isEqualTo(id),
			() -> assertThat(uebung.getName()).isEqualTo("Wettkampfbankdrücken (pausiert)"),
			() -> assertThat(uebung.getUebungsart()).isEqualTo(Uebungsart.GRUNDUEBUNG),
			() -> assertThat(uebung.getUebungskategorie()).isEqualTo(Uebungskategorie.WETTKAMPF_BANKDRUECKEN),
			() -> assertThat(uebung.getBelastungsfaktor()).isEqualTo(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("Getter besitzen")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN_ID),
			() -> assertThat(sut.getName()).isEqualTo("Wettkampfbankdrücken (pausiert)"),
			() -> assertThat(sut.getUebungsart()).isEqualTo(Uebungsart.GRUNDUEBUNG),
			() -> assertThat(sut.getUebungskategorie()).isEqualTo(Uebungskategorie.WETTKAMPF_BANKDRUECKEN),
			() -> assertThat(sut.getBelastungsfaktor()).isEqualTo(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("Setter besitzen")
	void test04()
	{
		final var id = new Primaerschluessel();
		final var uebung = new Uebung();
		uebung.setPrimaerschluessel(id);
		uebung.setName("Wettkampfbankdrücken (pausiert)");
		uebung.setUebungsart(Uebungsart.GRUNDUEBUNG);
		uebung.setUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN);
		uebung.setBelastungsfaktor(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);

		assertAll(
			() -> assertThat(uebung.getPrimaerschluessel()).isEqualTo(id),
			() -> assertThat(uebung.getName()).isEqualTo("Wettkampfbankdrücken (pausiert)"),
			() -> assertThat(uebung.getUebungsart()).isEqualTo(Uebungsart.GRUNDUEBUNG),
			() -> assertThat(uebung.getUebungskategorie()).isEqualTo(Uebungskategorie.WETTKAMPF_BANKDRUECKEN),
			() -> assertThat(uebung.getBelastungsfaktor()).isEqualTo(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	void test05()
	{
		final var andereUebung = new Uebung();
		andereUebung.setPrimaerschluessel(new Primaerschluessel());

		final var uebungMitGleicherId = new Uebung();
		uebungMitGleicherId.setPrimaerschluessel(sut.getPrimaerschluessel());

		assertAll(
			() -> assertThat(sut).isEqualTo(sut),
			() -> assertThat(sut).isNotNull(),
			() -> assertThat(sut).isNotEqualTo(Testdaten.AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut).isNotEqualTo(andereUebung),
			() -> assertThat(sut).isEqualTo(uebungMitGleicherId),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andereUebung.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "Uebung{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}

	@Test
	@DisplayName("einen Kraftwert hinzufügen können")
	void test07()
	{
		final var kraftwert = new Kraftwert(
			new Primaerschluessel(),
			100,
			Testdaten.BENUTZER_JUSTIN.getKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			sut,
			Testdaten.BENUTZER_JUSTIN);

		assertThat(sut.getKraftwerte()).contains(kraftwert);
	}
}
