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
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import de.justinharder.trainharder.setup.Testdaten;

public class KraftwertSollte
{
	private Kraftwert sut;

	@BeforeEach
	public void setup()
	{
		sut = Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	public void test01()
	{
		org.hamcrest.MatcherAssert.assertThat(Kraftwert.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor haben")
	public void test02()
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
	public void test03()
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
	public void test04()
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
	@SuppressWarnings("unlikely-arg-type")
	public void test05()
	{
		final var andererKraftwert = new Kraftwert();
		andererKraftwert.setPrimaerschluessel(new Primaerschluessel());

		final var kraftwertMitGleicherId = new Kraftwert();
		kraftwertMitGleicherId.setPrimaerschluessel(sut.getPrimaerschluessel());

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.AUTHENTIFIZIERUNG_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(andererKraftwert)).isEqualTo(false),
			() -> assertThat(sut.equals(kraftwertMitGleicherId)).isEqualTo(true),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andererKraftwert));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test06()
	{
		final var erwartet = "Kraftwert{ID=" + sut.getPrimaerschluessel().getId().toString() + "}";

		assertThat(sut.toString()).isEqualTo(erwartet);
	}
}
