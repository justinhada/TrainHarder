package de.justinharder.old.persistence;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.model.Authentifizierung;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static de.justinharder.old.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("AuthentifizierungJpaRepository sollte")
class AuthentifizierungJpaRepositoryTest
{
	@Inject
	AuthentifizierungJpaRepository sut;

	@Inject
	EntityManager entityManager;

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeMitBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeMitMail(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeMitBenutzername(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeMitResetUuid(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(AUTHENTIFIZIERUNG_JUSTIN, AUTHENTIFIZIERUNG_EDUARD);
	}

	@Test
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(AUTHENTIFIZIERUNG_JUSTIN.getId())).contains(AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut.finde(AUTHENTIFIZIERUNG_EDUARD.getId())).contains(AUTHENTIFIZIERUNG_EDUARD),
			() -> assertThat(sut.finde(new ID())).isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("erstellen")
	void test04()
	{
		var authentifizierung = new Authentifizierung(
			new ID(),
			"justinharder@t-online.de",
			"lololol",
			PASSWORT);

		sut.speichere(authentifizierung);

		assertThat(entityManager.find(Authentifizierung.class, authentifizierung.getId())).isEqualTo(authentifizierung);
	}

	@Test
	@Disabled
	@Transactional
	@DisplayName("aktualisieren")
	void test05()
	{
		var authentifizierung = AUTHENTIFIZIERUNG_JUSTIN
			.setAktiv(true);

		sut.speichere(authentifizierung);
		var ergebnis = entityManager.find(Authentifizierung.class, authentifizierung.getId());

		assertAll(
			() -> assertThat(ergebnis.getId()).isEqualTo(authentifizierung.getId()),
			() -> assertThat(ergebnis.getMail()).isEqualTo(authentifizierung.getMail()),
			() -> assertThat(ergebnis.getBenutzername()).isEqualTo(authentifizierung.getBenutzername()),
			() -> assertThat(ergebnis.getPasswort()).isEqualTo(authentifizierung.getPasswort()),
			() -> assertThat(ergebnis.isAktiv()).isEqualTo(authentifizierung.isAktiv()));
	}

	@Test
	@DisplayName("mit Benutzer finden")
	void test06()
	{
		assertAll(
			() -> assertThat(sut.findeMitBenutzer(BENUTZER_JUSTIN.getId())).contains(AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut.findeMitBenutzer(BENUTZER_EDUARD.getId())).contains(AUTHENTIFIZIERUNG_EDUARD),
			() -> assertThat(sut.findeMitBenutzer(new ID())).isEmpty());
	}

	@Test
	@DisplayName("mit Mail finden")
	void test07()
	{
		assertAll(
			() -> assertThat(sut.findeMitMail(AUTHENTIFIZIERUNG_JUSTIN.getMail())).contains(AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut.findeMitMail(AUTHENTIFIZIERUNG_EDUARD.getMail())).contains(
				AUTHENTIFIZIERUNG_EDUARD),
			() -> assertThat(sut.findeMitMail("nicht@existent.de")).isEmpty());
	}

	@Test
	@DisplayName("mit Benutzername finden")
	void test08()
	{
		assertAll(
			() -> assertThat(sut.findeMitBenutzername(AUTHENTIFIZIERUNG_JUSTIN.getBenutzername())).contains(
				AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut.findeMitBenutzername(AUTHENTIFIZIERUNG_EDUARD.getBenutzername())).contains(
				AUTHENTIFIZIERUNG_EDUARD),
			() -> assertThat(sut.findeMitBenutzername("nichtexistent")).isEmpty());
	}

	@Test
	@DisplayName("mit ResetUUID finden")
	void test09()
	{
		assertAll(
			() -> assertThat(sut.findeMitResetUuid(AUTHENTIFIZIERUNG_JUSTIN.getResetUuid())).contains(
				AUTHENTIFIZIERUNG_JUSTIN),
			() -> assertThat(sut.findeMitResetUuid(UUID.randomUUID())).isEmpty());
	}
}
