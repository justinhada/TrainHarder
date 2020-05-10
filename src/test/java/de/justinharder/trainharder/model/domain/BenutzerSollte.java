package de.justinharder.trainharder.model.domain;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.enums.Doping;
import de.justinharder.trainharder.model.domain.enums.Erfahrung;
import de.justinharder.trainharder.model.domain.enums.Ernaehrung;
import de.justinharder.trainharder.model.domain.enums.Geschlecht;
import de.justinharder.trainharder.model.domain.enums.Kraftlevel;
import de.justinharder.trainharder.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.trainharder.model.domain.enums.Schlafqualitaet;
import de.justinharder.trainharder.model.domain.enums.Stress;
import de.justinharder.trainharder.model.domain.enums.Wiederholungen;
import de.justinharder.trainharder.setup.Testdaten;

public class BenutzerSollte
{
	private Benutzer sut;

	@BeforeEach
	public void setup()
	{
		sut = Testdaten.BENUTZER_JUSTIN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	public void test01()
	{
		org.hamcrest.MatcherAssert.assertThat(Benutzer.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor haben")
	public void test02()
	{
		final var benutzer = new Benutzer(
			"Justin",
			"Harder",
			21,
			Geschlecht.MAENNLICH,
			Erfahrung.BEGINNER,
			Ernaehrung.GUT,
			Schlafqualitaet.GUT,
			Stress.MITTELMAESSIG,
			Doping.NEIN,
			Regenerationsfaehigkeit.GUT,
			new Authentifizierung(
				"mail@justinharder.de",
				"harder",
				"JustinHarder98"));

		assertAll(
			() -> assertThat(benutzer.getId()).isEqualTo(0),
			() -> assertThat(benutzer.getVorname()).isEqualTo("Justin"),
			() -> assertThat(benutzer.getNachname()).isEqualTo("Harder"),
			() -> assertThat(benutzer.getLebensalter()).isEqualTo(21),
			() -> assertThat(benutzer.getKraftlevel()).isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(benutzer.getGeschlecht()).isEqualTo(Geschlecht.MAENNLICH),
			() -> assertThat(benutzer.getErfahrung()).isEqualTo(Erfahrung.BEGINNER),
			() -> assertThat(benutzer.getErnaehrung()).isEqualTo(Ernaehrung.GUT),
			() -> assertThat(benutzer.getSchlafqualitaet()).isEqualTo(Schlafqualitaet.GUT),
			() -> assertThat(benutzer.getStress()).isEqualTo(Stress.MITTELMAESSIG),
			() -> assertThat(benutzer.getDoping()).isEqualTo(Doping.NEIN),
			() -> assertThat(benutzer.getRegenerationsfaehigkeit()).isEqualTo(Regenerationsfaehigkeit.GUT),
			() -> assertThat(benutzer.getAuthentifizierung().getId()).isEqualTo(0),
			() -> assertThat(benutzer.getAuthentifizierung().getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(benutzer.getAuthentifizierung().getBenutzername()).isEqualTo("harder"),
			() -> assertThat(benutzer.getAuthentifizierung().getPasswort()).isEqualTo("JustinHarder98"),
			() -> assertThat(benutzer.getAuthentifizierung().getBenutzer()).isEqualTo(benutzer));
	}

	@Test
	@DisplayName("Getter besitzen")
	public void test03()
	{
		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(1),
			() -> assertThat(sut.getVorname()).isEqualTo("Justin"),
			() -> assertThat(sut.getNachname()).isEqualTo("Harder"),
			() -> assertThat(sut.getLebensalter()).isEqualTo(21),
			() -> assertThat(sut.getKraftlevel()).isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(sut.getGeschlecht()).isEqualTo(Geschlecht.MAENNLICH),
			() -> assertThat(sut.getErfahrung()).isEqualTo(Erfahrung.BEGINNER),
			() -> assertThat(sut.getErnaehrung()).isEqualTo(Ernaehrung.GUT),
			() -> assertThat(sut.getSchlafqualitaet()).isEqualTo(Schlafqualitaet.GUT),
			() -> assertThat(sut.getStress()).isEqualTo(Stress.MITTELMAESSIG),
			() -> assertThat(sut.getDoping()).isEqualTo(Doping.NEIN),
			() -> assertThat(sut.getRegenerationsfaehigkeit()).isEqualTo(Regenerationsfaehigkeit.GUT),
			() -> assertThat(sut.getAktuelleKoerpergroesse()).isEqualTo(178),
			() -> assertThat(sut.getAktuellesKoerpergewicht()).isEqualTo(90),
			() -> assertThat(sut.getKoerpermessungen()).isEqualTo(List.of(Testdaten.KOERPERMESSUNG_JUSTIN)),
			() -> assertThat(sut.getAuthentifizierung()).isEqualTo(Testdaten.AUTHENTIFIZIERUNG_JUSTIN));
	}

	@Test
	@DisplayName("Setter besitzen")
	public void test04()
	{
		final var benutzer = new Benutzer();
		benutzer.setId(0);
		benutzer.setVorname("Justin");
		benutzer.setNachname("Harder");
		benutzer.setLebensalter(21);
		benutzer.setKraftlevel(Kraftlevel.CLASS_5);
		benutzer.setGeschlecht(Geschlecht.MAENNLICH);
		benutzer.setErfahrung(Erfahrung.BEGINNER);
		benutzer.setErnaehrung(Ernaehrung.GUT);
		benutzer.setSchlafqualitaet(Schlafqualitaet.GUT);
		benutzer.setStress(Stress.MITTELMAESSIG);
		benutzer.setDoping(Doping.NEIN);
		benutzer.setRegenerationsfaehigkeit(Regenerationsfaehigkeit.GUT);
		benutzer.setAuthentifizierung(
			new Authentifizierung(
				"mail@justinharder.de",
				"harder",
				"JustinHarder98"));

		assertAll(
			() -> assertThat(benutzer.getId()).isEqualTo(0),
			() -> assertThat(benutzer.getVorname()).isEqualTo("Justin"),
			() -> assertThat(benutzer.getNachname()).isEqualTo("Harder"),
			() -> assertThat(benutzer.getLebensalter()).isEqualTo(21),
			() -> assertThat(benutzer.getKraftlevel()).isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(benutzer.getGeschlecht()).isEqualTo(Geschlecht.MAENNLICH),
			() -> assertThat(benutzer.getErfahrung()).isEqualTo(Erfahrung.BEGINNER),
			() -> assertThat(benutzer.getErnaehrung()).isEqualTo(Ernaehrung.GUT),
			() -> assertThat(benutzer.getSchlafqualitaet()).isEqualTo(Schlafqualitaet.GUT),
			() -> assertThat(benutzer.getStress()).isEqualTo(Stress.MITTELMAESSIG),
			() -> assertThat(benutzer.getDoping()).isEqualTo(Doping.NEIN),
			() -> assertThat(benutzer.getRegenerationsfaehigkeit()).isEqualTo(Regenerationsfaehigkeit.GUT),
			() -> assertThat(benutzer.getAuthentifizierung().getId()).isEqualTo(0),
			() -> assertThat(benutzer.getAuthentifizierung().getMail()).isEqualTo("mail@justinharder.de"),
			() -> assertThat(benutzer.getAuthentifizierung().getBenutzername()).isEqualTo("harder"),
			() -> assertThat(benutzer.getAuthentifizierung().getPasswort()).isEqualTo("JustinHarder98"),
			() -> assertThat(benutzer.getAuthentifizierung().getBenutzer()).isEqualTo(benutzer));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	public void test05()
	{
		final var andererBenutzer = new Benutzer();
		andererBenutzer.setId(2);

		final var benutzerMitGleicherId = new Benutzer();
		benutzerMitGleicherId.setId(1);

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.AUTHENTIFIZIERUNG_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(andererBenutzer)).isEqualTo(false),
			() -> assertThat(sut.equals(benutzerMitGleicherId)).isEqualTo(true),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andererBenutzer));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test06()
	{
		assertThat(sut.toString()).isEqualTo("Benutzer{ID=1}");
	}

	@Test
	@DisplayName("einen Kraftwert hinzufügen können")
	public void test07()
	{
		final var kraftwert = new Kraftwert(
			100,
			sut.getAktuellesKoerpergewicht(),
			LocalDate.now(),
			Wiederholungen.ONE_REP_MAX,
			Testdaten.WETTKAMPFBANKDRUECKEN,
			sut);

		assertThat(sut.getKraftwerte().get(0)).isEqualTo(kraftwert);
	}
}
