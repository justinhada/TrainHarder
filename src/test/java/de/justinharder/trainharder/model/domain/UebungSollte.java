package de.justinharder.trainharder.model.domain;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import de.justinharder.trainharder.setup.Testdaten;

public class UebungSollte
{
	private Uebung sut;

	@BeforeEach
	public void setup()
	{
		sut = Testdaten.WETTKAMPFBANKDRUECKEN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	public void test01()
	{
		org.hamcrest.MatcherAssert.assertThat(Uebung.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor haben")
	public void test02()
	{
		final var uebung = new Uebung(
			"Wettkampfbankdrücken (pausiert)",
			Uebungsart.GRUNDUEBUNG,
			Uebungskategorie.WETTKAMPF_BANKDRUECKEN,
			Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);

		assertAll(
			() -> assertThat(uebung.getId()).isEqualTo(0),
			() -> assertThat(uebung.getName()).isEqualTo("Wettkampfbankdrücken (pausiert)"),
			() -> assertThat(uebung.getUebungsart()).isEqualTo(Uebungsart.GRUNDUEBUNG),
			() -> assertThat(uebung.getUebungskategorie()).isEqualTo(Uebungskategorie.WETTKAMPF_BANKDRUECKEN),
			() -> assertThat(uebung.getBelastungsfaktor()).isEqualTo(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("Getter besitzen")
	public void test03()
	{
		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(1),
			() -> assertThat(sut.getName()).isEqualTo("Wettkampfbankdrücken (pausiert)"),
			() -> assertThat(sut.getUebungsart()).isEqualTo(Uebungsart.GRUNDUEBUNG),
			() -> assertThat(sut.getUebungskategorie()).isEqualTo(Uebungskategorie.WETTKAMPF_BANKDRUECKEN),
			() -> assertThat(sut.getBelastungsfaktor()).isEqualTo(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("Setter besitzen")
	public void test04()
	{
		final var uebung = new Uebung();
		uebung.setId(0);
		uebung.setName("Wettkampfbankdrücken (pausiert)");
		uebung.setUebungsart(Uebungsart.GRUNDUEBUNG);
		uebung.setUebungskategorie(Uebungskategorie.WETTKAMPF_BANKDRUECKEN);
		uebung.setBelastungsfaktor(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN);

		assertAll(
			() -> assertThat(uebung.getId()).isEqualTo(0),
			() -> assertThat(uebung.getName()).isEqualTo("Wettkampfbankdrücken (pausiert)"),
			() -> assertThat(uebung.getUebungsart()).isEqualTo(Uebungsart.GRUNDUEBUNG),
			() -> assertThat(uebung.getUebungskategorie()).isEqualTo(Uebungskategorie.WETTKAMPF_BANKDRUECKEN),
			() -> assertThat(uebung.getBelastungsfaktor()).isEqualTo(Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	public void test05()
	{
		final var andereUebung = new Uebung();
		andereUebung.setId(2);

		final var uebungMitGleicherId = new Uebung();
		uebungMitGleicherId.setId(1);

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.AUTHENTIFIZIERUNG_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(andereUebung)).isEqualTo(false),
			() -> assertThat(sut.equals(uebungMitGleicherId)).isEqualTo(true),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andereUebung));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test06()
	{
		assertThat(sut.toString()).isEqualTo("Uebung{ID=1}");
	}

	@Test
	@DisplayName("einen Kraftwert hinzufügen können")
	public void test07()
	{
		final var kraftwert = new Kraftwert(
			100,
			Testdaten.BENUTZER_JUSTIN.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			sut,
			Testdaten.BENUTZER_JUSTIN);

		assertThat(sut.getKraftwerte().get(0)).isEqualTo(kraftwert);
	}
}
