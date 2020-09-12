package de.justinharder.trainharder.view.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.setup.Testdaten;

class BelastungsfaktorDtoSollte
{
	private BelastungsfaktorDto sut;

	private String primaerschluessel;

	@BeforeEach
	void setup()
	{
		primaerschluessel = new Primaerschluessel().getId().toString();
		sut = new BelastungsfaktorDto(primaerschluessel, 0.0, 1.0, 0.0, 0.7, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1);
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(primaerschluessel),
			() -> assertThat(sut.getBack()).isEqualTo(0.0),
			() -> assertThat(sut.getBenchpress()).isEqualTo(1.0),
			() -> assertThat(sut.getBiceps()).isEqualTo(0.0),
			() -> assertThat(sut.getChest()).isEqualTo(1.0),
			() -> assertThat(sut.getCore()).isEqualTo(0.0),
			() -> assertThat(sut.getDeadlift()).isEqualTo(0.0),
			() -> assertThat(sut.getGlutes()).isEqualTo(0.0),
			() -> assertThat(sut.getHamstrings()).isEqualTo(0.0),
			() -> assertThat(sut.getQuads()).isEqualTo(0.0),
			() -> assertThat(sut.getShoulder()).isEqualTo(0.1),
			() -> assertThat(sut.getSquat()).isEqualTo(0.0),
			() -> assertThat(sut.getTriceps()).isEqualTo(0.7));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
	{
		sut = new BelastungsfaktorDto()
			.setPrimaerschluessel(primaerschluessel)
			.setBack(0.0)
			.setBenchpress(1.0)
			.setBiceps(0.0)
			.setChest(1.0)
			.setCore(0.0)
			.setDeadlift(0.0)
			.setGlutes(0.0)
			.setHamstrings(0.0)
			.setQuads(0.0)
			.setShoulder(0.1)
			.setSquat(0.0)
			.setTriceps(0.7);

		assertAll(
			() -> assertThat(sut.getPrimaerschluessel()).isEqualTo(primaerschluessel),
			() -> assertThat(sut.getBack()).isEqualTo(0.0),
			() -> assertThat(sut.getBenchpress()).isEqualTo(1.0),
			() -> assertThat(sut.getBiceps()).isEqualTo(0.0),
			() -> assertThat(sut.getChest()).isEqualTo(1.0),
			() -> assertThat(sut.getCore()).isEqualTo(0.0),
			() -> assertThat(sut.getDeadlift()).isEqualTo(0.0),
			() -> assertThat(sut.getGlutes()).isEqualTo(0.0),
			() -> assertThat(sut.getHamstrings()).isEqualTo(0.0),
			() -> assertThat(sut.getQuads()).isEqualTo(0.0),
			() -> assertThat(sut.getShoulder()).isEqualTo(0.1),
			() -> assertThat(sut.getSquat()).isEqualTo(0.0),
			() -> assertThat(sut.getTriceps()).isEqualTo(0.7));
	}

	@Test
	@DisplayName("sich vergleichen")
	@SuppressWarnings("unlikely-arg-type")
	void test05()
	{
		final var anderesBelastungsfaktorDto = new BelastungsfaktorDto(new Primaerschluessel().getId().toString(),
			1.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.2, 0.0, 1.0, 1.0, 0.5, 0.0);

		assertAll(
			() -> assertThat(sut.equals(sut)).isEqualTo(true),
			() -> assertThat(sut.equals(null)).isEqualTo(false),
			() -> assertThat(sut.equals(Testdaten.AUTHENTIFIZIERUNG_JUSTIN)).isEqualTo(false),
			() -> assertThat(sut.equals(anderesBelastungsfaktorDto)).isEqualTo(false),
			() -> assertThat(sut.hashCode()).isNotEqualTo(anderesBelastungsfaktorDto.hashCode()));
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test06()
	{
		final var erwartet = "BelastungsfaktorDto(primaerschluessel=" + primaerschluessel
			+ ", squat=0.0, benchpress=1.0, deadlift=0.0, triceps=0.7, chest=1.0, core=0.0, back=0.0, biceps=0.0, glutes=0.0, quads=0.0, hamstrings=0.0, shoulder=0.1)";

		assertThat(sut.toString()).isEqualTo(erwartet);
	}
}
