package de.justinharder.old.domain.services.berechnung.anpassungsfaktor;

import de.justinharder.old.domain.services.berechnung.anpassungsfaktor.Anpassungsfaktor;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Anpassungsfaktor sollte")
class AnpassungsfaktorTest
{
	private Anpassungsfaktor sut;

	@BeforeEach
	void setup()
	{
		sut = new Anpassungsfaktor()
			.mitAlter(-2)
			.mitKoerpergewicht(2)
			.mitKoerpergroesse(4)
			.mitKraftlevel(-3)
			.mitGeschlecht(5)
			.mitErfahrung(-1)
			.mitErnaehrung(1)
			.mitSchlafqualitaet(-3)
			.mitStress(1)
			.mitDoping(3)
			.mitRegenerationsfaehigkeit(2);
	}

	@Test
	@DisplayName("erstellt werden können")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getAlter()).isEqualTo(-2),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(2),
			() -> assertThat(sut.getKoerpergroesse()).isEqualTo(4),
			() -> assertThat(sut.getKraftlevel()).isEqualTo(-3),
			() -> assertThat(sut.getGeschlecht()).isEqualTo(5),
			() -> assertThat(sut.getErfahrung()).isEqualTo(-1),
			() -> assertThat(sut.getErnaehrung()).isEqualTo(1),
			() -> assertThat(sut.getSchlafqualitaet()).isEqualTo(-3),
			() -> assertThat(sut.getStress()).isEqualTo(1),
			() -> assertThat(sut.getDoping()).isEqualTo(3),
			() -> assertThat(sut.getRegenerationsfaehigkeit()).isEqualTo(2),
			() -> assertThat(sut.werteAus()).isEqualTo(9));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Anpassungsfaktor.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode besitzen")
	void test03()
	{
		assertThat(sut).hasToString(
			"Anpassungsfaktor(alter=-2, koerpergewicht=2, koerpergroesse=4, kraftlevel=-3, geschlecht=5, erfahrung=-1, ernaehrung=1, schlafqualitaet=-3, stress=1, doping=3, regenerationsfaehigkeit=2)");
	}
}
