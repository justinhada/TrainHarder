package de.justinharder.old.persistence;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.model.Koerpermessung;
import de.justinharder.old.domain.model.attribute.Koerpermasse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static de.justinharder.old.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("KoerpermessungJpaRepository sollte")
class KoerpermessungJpaRepositoryTest
{
	@Inject
	KoerpermessungJpaRepository sut;

	@Inject
	EntityManager entityManager;

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.findeAlleMitBenutzer(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(KOERPERMESSUNG_JUSTIN, KOERPERMESSUNG_EDUARD);
	}

	@Test
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(KOERPERMESSUNG_JUSTIN.getId())).contains(KOERPERMESSUNG_JUSTIN),
			() -> assertThat(sut.finde(KOERPERMESSUNG_EDUARD.getId())).contains(KOERPERMESSUNG_EDUARD),
			() -> assertThat(sut.finde(new ID())).isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("erstellen")
	void test04()
	{
		var koerpermessung = new Koerpermessung(
			new ID(),
			LocalDate.now(),
			new Koerpermasse(new BigDecimal(178), new BigDecimal(90), new BigDecimal(25)),
			2500,
			2900);

		sut.speichere(koerpermessung);

		assertThat(entityManager.find(Koerpermessung.class, koerpermessung.getId())).isEqualTo(koerpermessung);
	}

	@Test
	@Disabled
	@Transactional
	@DisplayName("aktualisieren")
	void test05()
	{
		var koerpermessung = KOERPERMESSUNG_JUSTIN
			.setKalorieneinnahme(1900);

		sut.speichere(koerpermessung);
		var ergebnis = entityManager.find(Koerpermessung.class, koerpermessung.getId());

		assertAll(
			() -> assertThat(ergebnis.getId()).isEqualTo(koerpermessung.getId()),
			() -> assertThat(ergebnis.getDatum()).isEqualTo(koerpermessung.getDatum()),
			() -> assertThat(ergebnis.getKoerpermasse()).isEqualTo(koerpermessung.getKoerpermasse()),
			() -> assertThat(ergebnis.getKalorieneinnahme()).isEqualTo(koerpermessung.getKalorieneinnahme()),
			() -> assertThat(ergebnis.getKalorienverbrauch()).isEqualTo(koerpermessung.getKalorienverbrauch()));
	}

	@Test
	@DisplayName("alle mit Benutzer finden")
	void test06()
	{
		assertAll(
			() -> assertThat(sut.findeAlleMitBenutzer(BENUTZER_JUSTIN.getId())).containsExactlyInAnyOrder(
				KOERPERMESSUNG_JUSTIN),
			() -> assertThat(sut.findeAlleMitBenutzer(BENUTZER_EDUARD.getId())).containsExactlyInAnyOrder(
				KOERPERMESSUNG_EDUARD),
			() -> assertThat(sut.findeAlleMitBenutzer(new ID())).isEmpty());
	}
}
