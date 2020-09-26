package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
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
import static org.junit.jupiter.api.Assertions.assertAll;

class KraftwertSollte
{
	private Kraftwert sut;

	@BeforeEach
	void setup()
	{
		sut = Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	void test01()
	{
		org.hamcrest.MatcherAssert.assertThat(Kraftwert.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor haben")
	void test02()
	{
		final var id = new Primaerschluessel();
		final var kraftwert = new Kraftwert(
			id,
			100,
			Testdaten.BENUTZER_JUSTIN.getKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(kraftwert.getPrimaerschluessel()).isEqualTo(id),
			() -> assertThat(kraftwert.getMaximum()).isEqualTo(100),
			() -> assertThat(kraftwert.getKoerpergewicht())
				.isEqualTo(Testdaten.BENUTZER_JUSTIN.getKoerpergewicht()),
			() -> assertThat(kraftwert.getDatum()).isEqualTo(LocalDate.now()),
			() -> assertThat(kraftwert.getWiederholungen()).isEqualTo(Wiederholungen.ONE_REP_MAX),
			() -> assertThat(kraftwert.getUebung()).isEqualTo(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(kraftwert.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("Getter besitzen")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN_ID),
			() -> assertThat(sut.getMaximum()).isEqualTo(100),
			() -> assertThat(sut.getKoerpergewicht())
				.isEqualTo(Testdaten.BENUTZER_JUSTIN.getKoerpergewicht()),
			() -> assertThat(sut.getDatum()).isEqualTo(LocalDate.now()),
			() -> assertThat(sut.getWiederholungen()).isEqualTo(Wiederholungen.ONE_REP_MAX),
			() -> assertThat(sut.getUebung()).isEqualTo(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("Setter besitzen")
	void test04()
	{
		final var id = new Primaerschluessel();
		final var kraftwert = new Kraftwert();
		kraftwert.setPrimaerschluessel(id);
		kraftwert.setMaximum(100);
		kraftwert.setKoerpergewicht(Testdaten.BENUTZER_JUSTIN.getKoerpergewicht());
		kraftwert.setDatum(LocalDate.now());
		kraftwert.setWiederholungen(Wiederholungen.ONE_REP_MAX);
		kraftwert.setUebung(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN);
		kraftwert.setBenutzer(Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(kraftwert.getPrimaerschluessel()).isEqualTo(id),
			() -> assertThat(kraftwert.getMaximum()).isEqualTo(100),
			() -> assertThat(kraftwert.getKoerpergewicht())
				.isEqualTo(Testdaten.BENUTZER_JUSTIN.getKoerpergewicht()),
			() -> assertThat(kraftwert.getDatum()).isEqualTo(LocalDate.now()),
			() -> assertThat(kraftwert.getWiederholungen()).isEqualTo(Wiederholungen.ONE_REP_MAX),
			() -> assertThat(kraftwert.getUebung()).isEqualTo(Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(kraftwert.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test05()
	{
		EqualsVerifier.forClass(Kraftwert.class)
			.withPrefabValues(Uebung.class, Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN, Testdaten.UEBUNG_LOWBAR_KNIEBEUGE)
			.withPrefabValues(Benutzer.class, Testdaten.BENUTZER_JUSTIN, Testdaten.BENUTZER_EDUARD)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.SURROGATE_KEY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "Kraftwert{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut).hasToString(erwartet);
	}
}
