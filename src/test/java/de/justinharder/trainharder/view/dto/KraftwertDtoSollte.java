package de.justinharder.trainharder.view.dto;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KraftwertDtoSollte
{
	private static final String PRIMAERSCHLUESSEL = new Primaerschluessel().getId().toString();
	private static final String GEWICHT = "100.00";
	private static final String KOERPERGEWICHT = "75.00";
	private static final String DATUM = "22.08.2020";
	private static final String WIEDERHOLUNGEN = "1RM";

	private KraftwertDto sut;

	@BeforeEach
	void setup()
	{
		sut = new KraftwertDto(PRIMAERSCHLUESSEL, GEWICHT, KOERPERGEWICHT, DATUM, WIEDERHOLUNGEN);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
	{
		sut = new KraftwertDto()
			.setPrimaerschluessel(PRIMAERSCHLUESSEL)
			.setGewicht(GEWICHT)
			.setKoerpergewicht(KOERPERGEWICHT)
			.setDatum(DATUM)
			.setWiederholungen(WIEDERHOLUNGEN);

		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(PRIMAERSCHLUESSEL),
			() -> assertThat(sut.getGewicht()).isEqualTo(GEWICHT),
			() -> assertThat(sut.getKoerpergewicht()).isEqualTo(KOERPERGEWICHT),
			() -> assertThat(sut.getDatum()).isEqualTo(DATUM),
			() -> assertThat(sut.getWiederholungen()).isEqualTo(WIEDERHOLUNGEN));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test03()
	{
		EqualsVerifier.forClass(KraftwertDto.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test04()
	{
		assertThat(sut).hasToString("KraftwertDto(super=EntitaetDto(primaerschluessel=" + PRIMAERSCHLUESSEL + "), gewicht=100.00, koerpergewicht=75.00, datum=22.08.2020, wiederholungen=1RM)");
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new KraftwertDto(null, GEWICHT, KOERPERGEWICHT, DATUM, WIEDERHOLUNGEN)),
			() -> assertThrows(NullPointerException.class, () -> new KraftwertDto(PRIMAERSCHLUESSEL, null, KOERPERGEWICHT, DATUM, WIEDERHOLUNGEN)),
			() -> assertThrows(NullPointerException.class, () -> new KraftwertDto(PRIMAERSCHLUESSEL, GEWICHT, null, DATUM, WIEDERHOLUNGEN)),
			() -> assertThrows(NullPointerException.class, () -> new KraftwertDto(PRIMAERSCHLUESSEL, GEWICHT, KOERPERGEWICHT, null, WIEDERHOLUNGEN)),
			() -> assertThrows(NullPointerException.class, () -> new KraftwertDto(PRIMAERSCHLUESSEL, GEWICHT, KOERPERGEWICHT, DATUM, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setPrimaerschluessel(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setGewicht(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setKoerpergewicht(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setDatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setWiederholungen(null)));
	}
}