package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import de.justinharder.trainharder.setup.Testdaten;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		assertThat(Uebung.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor haben")
	void test02()
	{
		var id = new Primaerschluessel();
		var uebung = new Uebung(
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
		var id = new Primaerschluessel();
		var uebung = new Uebung()
			.setPrimaerschluessel(id)
			.setName("Wettkampfbankdrücken (pausiert)")
			.setUebungsart(Uebungsart.GRUNDUEBUNG)
			.setUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN)
			.setBelastungsfaktor(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);

		assertAll(
			() -> assertThat(uebung.getPrimaerschluessel()).isEqualTo(id),
			() -> assertThat(uebung.getName()).isEqualTo("Wettkampfbankdrücken (pausiert)"),
			() -> assertThat(uebung.getUebungsart()).isEqualTo(Uebungsart.GRUNDUEBUNG),
			() -> assertThat(uebung.getUebungskategorie()).isEqualTo(Uebungskategorie.WETTKAMPF_BANKDRUECKEN),
			() -> assertThat(uebung.getBelastungsfaktor()).isEqualTo(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test05()
	{
		EqualsVerifier.forClass(Uebung.class)
			.withPrefabValues(Belastungsfaktor.class, Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN,
				Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE)
			.withPrefabValues(Kraftwert.class, Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
				Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.SURROGATE_KEY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		var erwartet = "Uebung{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}

	@Test
	@DisplayName("einen Kraftwert hinzufügen können")
	void test07()
	{
		var kraftwert = new Kraftwert(
			new Primaerschluessel(),
			100.0,
			Testdaten.BENUTZER_JUSTIN.getKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			sut,
			Testdaten.BENUTZER_JUSTIN);
		sut.fuegeKraftwertHinzu(kraftwert);

		assertThat(sut.getKraftwerte()).contains(kraftwert);
	}

	@Test
	@DisplayName("null validieren")
	void test08()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new Uebung(null, "Name", Uebungsart.GRUNDUEBUNG, Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
					new Belastungsfaktor())),
			() -> assertThrows(NullPointerException.class,
				() -> new Uebung(new Primaerschluessel(), null, Uebungsart.GRUNDUEBUNG,
					Uebungskategorie.WETTKAMPF_BANKDRUECKEN, new Belastungsfaktor())),
			() -> assertThrows(NullPointerException.class,
				() -> new Uebung(new Primaerschluessel(), "Name", null, Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
					new Belastungsfaktor())),
			() -> assertThrows(NullPointerException.class,
				() -> new Uebung(new Primaerschluessel(), "Name", Uebungsart.GRUNDUEBUNG, null,
					new Belastungsfaktor())),
			() -> assertThrows(NullPointerException.class,
				() -> new Uebung(new Primaerschluessel(), "Name", Uebungsart.GRUNDUEBUNG,
					Uebungskategorie.WETTKAMPF_BANKDRUECKEN, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setName(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebungsart(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setUebungskategorie(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBelastungsfaktor(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.fuegeKraftwertHinzu(null)));
	}
}
