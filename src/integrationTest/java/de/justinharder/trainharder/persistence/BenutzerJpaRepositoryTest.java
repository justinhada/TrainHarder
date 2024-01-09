package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.domain.model.Authentifizierung;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.attribute.Benutzerangabe;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.attribute.Name;
import de.justinharder.trainharder.domain.model.enums.*;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("BenutzerJpaRepository sollte")
class BenutzerJpaRepositoryTest
{
	@Inject
	BenutzerJpaRepository sut;

	@Inject
	EntityManager entityManager;

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeMitAuthentifizierung(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(BENUTZER_JUSTIN, BENUTZER_EDUARD);
	}

	@Test
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(BENUTZER_JUSTIN.getId())).contains(BENUTZER_JUSTIN),
			() -> assertThat(sut.finde(BENUTZER_EDUARD.getId())).contains(BENUTZER_EDUARD),
			() -> assertThat(sut.finde(new ID())).isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("erstellen")
	void test04()
	{
		var authentifizierung = new Authentifizierung(new ID(), "nicoleharder@mail.de", "nicoleee", PASSWORT);
		entityManager.persist(authentifizierung);

		var benutzer = new Benutzer(
			new ID(),
			new Name("Nicole", "Harder"),
			LocalDate.of(2007, 2, 26),
			new Benutzerangabe(
				Geschlecht.WEIBLICH,
				Erfahrung.BEGINNER,
				Ernaehrung.SCHLECHT,
				Schlafqualitaet.GUT,
				Stress.MITTELMAESSIG,
				Doping.NEIN,
				Regenerationsfaehigkeit.SCHLECHT),
			authentifizierung);

		sut.speichere(benutzer);

		assertThat(entityManager.find(Benutzer.class, benutzer.getId())).isEqualTo(benutzer);
	}

	@Test
	@Disabled
	@Transactional
	@DisplayName("aktualisieren")
	void test05()
	{
		var benutzer = BENUTZER_EDUARD
			.setGeburtsdatum(LocalDate.of(1997, 12, 6));

		sut.speichere(benutzer);
		var ergebnis = entityManager.find(Benutzer.class, benutzer.getId());

		assertAll(
			() -> assertThat(ergebnis.getId()).isEqualTo(benutzer.getId()),
			() -> assertThat(ergebnis.getName()).isEqualTo(benutzer.getName()),
			() -> assertThat(ergebnis.getGeburtsdatum()).isEqualTo(benutzer.getGeburtsdatum()),
			() -> assertThat(ergebnis.getBenutzerangabe()).isEqualTo(benutzer.getBenutzerangabe()),
			() -> assertThat(ergebnis.getAuthentifizierung()).isEqualTo(benutzer.getAuthentifizierung()));
	}

	@Test
	@DisplayName("mit Authentifizierung finden")
	void test06()
	{
		assertAll(
			() -> assertThat(sut.findeMitAuthentifizierung(AUTHENTIFIZIERUNG_JUSTIN.getId())).contains(
				BENUTZER_JUSTIN),
			() -> assertThat(sut.findeMitAuthentifizierung(AUTHENTIFIZIERUNG_EDUARD.getId())).contains(
				BENUTZER_EDUARD),
			() -> assertThat(sut.findeMitAuthentifizierung(new ID())).isEmpty());
	}
}
