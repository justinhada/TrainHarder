package de.justinharder.powerlifting.model.domain;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.powerlifting.setup.Testdaten;

public class KoerpermessungSollte
{
	private Koerpermessung sut;

	@BeforeEach
	public void setup()
	{
		sut = Testdaten.KOERPERMESSUNG_JUSTIN;
	}

	@Test
	@DisplayName("einen NoArgsConstructor haben")
	public void test01()
	{
		org.hamcrest.MatcherAssert.assertThat(Koerpermessung.class, allOf(hasValidBeanConstructor()));
	}

	@Test
	@DisplayName("einen RequiredArgsConstructor haben")
	public void test02()
	{
		final var koerpermessung = new Koerpermessung(
			178,
			90,
			25,
			71,
			20,
			11,
			56,
			49,
			67,
			4,
			17.5,
			1900,
			2500,
			2900,
			25,
			LocalDate.now(),
			Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(koerpermessung.getId()).isEqualTo(0),
			() -> assertThat(koerpermessung.getKoerpergroesse()).isEqualTo(178),
			() -> assertThat(koerpermessung.getKoerpergewicht()).isEqualTo(90),
			() -> assertThat(koerpermessung.getBodyMassIndex()).isEqualTo(28.41),
			() -> assertThat(koerpermessung.getFatFreeMassIndex()).isEqualTo(21.43),
			() -> assertThat(koerpermessung.getKoerperfettAnteil()).isEqualTo(25),
			() -> assertThat(koerpermessung.getFettfreiesKoerpergewicht()).isEqualTo(71),
			() -> assertThat(koerpermessung.getSubkutanesFett()).isEqualTo(20),
			() -> assertThat(koerpermessung.getViszeralfett()).isEqualTo(11),
			() -> assertThat(koerpermessung.getKoerperwasser()).isEqualTo(56),
			() -> assertThat(koerpermessung.getSkelettmuskel()).isEqualTo(49),
			() -> assertThat(koerpermessung.getMuskelmasse()).isEqualTo(67),
			() -> assertThat(koerpermessung.getKnochenmasse()).isEqualTo(4),
			() -> assertThat(koerpermessung.getProtein()).isEqualTo(17.5),
			() -> assertThat(koerpermessung.getGrundumsatz()).isEqualTo(1900),
			() -> assertThat(koerpermessung.getEingenommeneKalorien()).isEqualTo(2500),
			() -> assertThat(koerpermessung.getVerbrannteKalorien()).isEqualTo(2900),
			() -> assertThat(koerpermessung.getBiologischesAlter()).isEqualTo(25),
			() -> assertThat(koerpermessung.getDatum()).isEqualTo(LocalDate.now()),
			() -> assertThat(koerpermessung.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("Getter besitzen")
	public void test03()
	{
		assertAll(
			() -> assertThat(sut.getId()).isEqualTo(0),
			() -> assertThat(sut.getKoerpergroesse()).isEqualTo(178),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(90),
			() -> assertThat(sut.getBodyMassIndex()).isEqualTo(28.41),
			() -> assertThat(sut.getFatFreeMassIndex()).isEqualTo(21.43),
			() -> assertThat(sut.getKoerperfettAnteil()).isEqualTo(25),
			() -> assertThat(sut.getFettfreiesKoerpergewicht()).isEqualTo(71),
			() -> assertThat(sut.getSubkutanesFett()).isEqualTo(20),
			() -> assertThat(sut.getViszeralfett()).isEqualTo(11),
			() -> assertThat(sut.getKoerperwasser()).isEqualTo(56),
			() -> assertThat(sut.getSkelettmuskel()).isEqualTo(49),
			() -> assertThat(sut.getMuskelmasse()).isEqualTo(67),
			() -> assertThat(sut.getKnochenmasse()).isEqualTo(4),
			() -> assertThat(sut.getProtein()).isEqualTo(17.5),
			() -> assertThat(sut.getGrundumsatz()).isEqualTo(1900),
			() -> assertThat(sut.getEingenommeneKalorien()).isEqualTo(2500),
			() -> assertThat(sut.getVerbrannteKalorien()).isEqualTo(2900),
			() -> assertThat(sut.getBiologischesAlter()).isEqualTo(25),
			() -> assertThat(sut.getDatum()).isEqualTo(LocalDate.now()),
			() -> assertThat(sut.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("Setter besitzen")
	public void test04()
	{
		final var koerpermessung = new Koerpermessung();
		koerpermessung.setId(0);
		koerpermessung.setKoerpergroesse(178);
		koerpermessung.setKoerpergewicht(90);
		koerpermessung.setBodyMassIndex(28.41);
		koerpermessung.setFatFreeMassIndex(21.43);
		koerpermessung.setKoerperfettAnteil(25);
		koerpermessung.setFettfreiesKoerpergewicht(71);
		koerpermessung.setSubkutanesFett(20);
		koerpermessung.setViszeralfett(11);
		koerpermessung.setKoerperwasser(56);
		koerpermessung.setSkelettmuskel(49);
		koerpermessung.setMuskelmasse(67);
		koerpermessung.setKnochenmasse(4);
		koerpermessung.setProtein(17.5);
		koerpermessung.setGrundumsatz(1900);
		koerpermessung.setEingenommeneKalorien(2500);
		koerpermessung.setVerbrannteKalorien(2900);
		koerpermessung.setBiologischesAlter(25);
		koerpermessung.setDatum(LocalDate.now());
		koerpermessung.setBenutzer(Testdaten.BENUTZER_JUSTIN);

		assertAll(
			() -> assertThat(koerpermessung.getId()).isEqualTo(0),
			() -> assertThat(koerpermessung.getKoerpergroesse()).isEqualTo(178),
			() -> assertThat(koerpermessung.getKoerpergewicht()).isEqualTo(90),
			() -> assertThat(koerpermessung.getBodyMassIndex()).isEqualTo(28.41),
			() -> assertThat(koerpermessung.getFatFreeMassIndex()).isEqualTo(21.43),
			() -> assertThat(koerpermessung.getKoerperfettAnteil()).isEqualTo(25),
			() -> assertThat(koerpermessung.getFettfreiesKoerpergewicht()).isEqualTo(71),
			() -> assertThat(koerpermessung.getSubkutanesFett()).isEqualTo(20),
			() -> assertThat(koerpermessung.getViszeralfett()).isEqualTo(11),
			() -> assertThat(koerpermessung.getKoerperwasser()).isEqualTo(56),
			() -> assertThat(koerpermessung.getSkelettmuskel()).isEqualTo(49),
			() -> assertThat(koerpermessung.getMuskelmasse()).isEqualTo(67),
			() -> assertThat(koerpermessung.getKnochenmasse()).isEqualTo(4),
			() -> assertThat(koerpermessung.getProtein()).isEqualTo(17.5),
			() -> assertThat(koerpermessung.getGrundumsatz()).isEqualTo(1900),
			() -> assertThat(koerpermessung.getEingenommeneKalorien()).isEqualTo(2500),
			() -> assertThat(koerpermessung.getVerbrannteKalorien()).isEqualTo(2900),
			() -> assertThat(koerpermessung.getBiologischesAlter()).isEqualTo(25),
			() -> assertThat(koerpermessung.getDatum()).isEqualTo(LocalDate.now()),
			() -> assertThat(koerpermessung.getBenutzer()).isEqualTo(Testdaten.BENUTZER_JUSTIN));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	public void test05()
	{
		final var andereKoerpermessung = new Koerpermessung();
		andereKoerpermessung.setId(1);

		final var koerpermessungMitGleicherId = new Koerpermessung();
		koerpermessungMitGleicherId.setId(0);

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.AUTHENTIFIZIERUNG_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(andereKoerpermessung)).isEqualTo(false),
			() -> assertThat(sut.equals(koerpermessungMitGleicherId)).isEqualTo(true),
			() -> assertThat(sut.hashCode()).isNotEqualTo(andereKoerpermessung));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	public void test06()
	{
		assertThat(sut.toString()).isEqualTo("Koerpermessung{ID=0}");
	}
}
