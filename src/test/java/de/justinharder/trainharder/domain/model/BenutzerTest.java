package de.justinharder.trainharder.domain.model;

import de.justinharder.trainharder.domain.model.attribute.Benutzerangabe;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.attribute.Name;
import de.justinharder.trainharder.domain.model.enums.*;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Benutzer sollte")
class BenutzerTest
{
	private static final ID ID = new ID();
	private static final Name NAME = new Name("Justin", "Harder");
	private static final LocalDate GEBURTSDATUM = LocalDate.of(1998, 12, 6);
	private static final Benutzerangabe BENUTZERANGABE = new Benutzerangabe(
		Geschlecht.MAENNLICH,
		Erfahrung.BEGINNER,
		Ernaehrung.GUT,
		Schlafqualitaet.GUT,
		Stress.MITTELMAESSIG,
		Doping.NEIN,
		Regenerationsfaehigkeit.GUT)
		.setKraftlevel(Kraftlevel.CLASS_5);
	private static final ID ID_AUTHENTIFIZIERUNG = new ID();
	private static final Authentifizierung AUTHENTIFIZIERUNG =
		new Authentifizierung(ID_AUTHENTIFIZIERUNG, "mail@justinharder.de", "harder", PASSWORT);

	private Benutzer sut;

	@BeforeEach
	void setup()
	{
		sut = new Benutzer(ID, NAME, GEBURTSDATUM, BENUTZERANGABE, AUTHENTIFIZIERUNG);
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test01()
	{
		var benutzer = new Benutzer()
			.setName(NAME)
			.setGeburtsdatum(GEBURTSDATUM)
			.setBenutzerangabe(BENUTZERANGABE)
			.setAuthentifizierung(AUTHENTIFIZIERUNG);

		assertAll(
			() -> assertThat(benutzer.getName()).isEqualTo(NAME),
			() -> assertThat(benutzer.getGeburtsdatum()).isEqualTo(GEBURTSDATUM),
			() -> assertThat(benutzer.getBenutzerangabe()).isEqualTo(BENUTZERANGABE),
			() -> assertThat(benutzer.getAuthentifizierung()).isEqualTo(AUTHENTIFIZIERUNG),
			() -> assertThat(new Benutzer().getKoerpergewicht()).isNull(),
			() -> assertThat(new Benutzer().getKoerpergroesse()).isNull());
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(Benutzer.class)
			.withPrefabValues(Authentifizierung.class, AUTHENTIFIZIERUNG_JUSTIN, AUTHENTIFIZIERUNG_EDUARD)
			.withPrefabValues(Koerpermessung.class, KOERPERMESSUNG_JUSTIN, KOERPERMESSUNG_EDUARD)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.SURROGATE_KEY)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test03()
	{
		assertThat(sut).hasToString("Benutzer{ID=" + sut.getId().getWert() + "}");
	}

	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzer(null, NAME, GEBURTSDATUM, BENUTZERANGABE, AUTHENTIFIZIERUNG)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzer(ID, null, GEBURTSDATUM, BENUTZERANGABE, AUTHENTIFIZIERUNG)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzer(ID, NAME, null, BENUTZERANGABE, AUTHENTIFIZIERUNG)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzer(ID, NAME, GEBURTSDATUM, null, AUTHENTIFIZIERUNG)),
			() -> assertThrows(NullPointerException.class,
				() -> new Benutzer(ID, NAME, GEBURTSDATUM, BENUTZERANGABE, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setName(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setGeburtsdatum(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBenutzerangabe(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setAuthentifizierung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.fuegeKoerpermessungHinzu(null)));
	}
}
