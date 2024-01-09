package de.justinharder.old.persistence;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.model.Kraftwert;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static de.justinharder.old.domain.model.enums.Wiederholungen.ONE_REP_MAX;
import static de.justinharder.old.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("KraftwertJpaRepository sollte")
class KraftwertJpaRepositoryTest
{
	@Inject
	KraftwertJpaRepository sut;

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
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(KRAFTWERT_WETTKAMPFBANKDRUECKEN,
			KRAFTWERT_LOWBAR_KNIEBEUGE, KRAFTWERT_KONVENTIONELLES_KREUZHEBEN);
	}

	@Test
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(KRAFTWERT_WETTKAMPFBANKDRUECKEN.getId())).contains(
				KRAFTWERT_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.finde(KRAFTWERT_LOWBAR_KNIEBEUGE.getId())).contains(KRAFTWERT_LOWBAR_KNIEBEUGE),
			() -> assertThat(sut.finde(KRAFTWERT_KONVENTIONELLES_KREUZHEBEN.getId())).contains(
				KRAFTWERT_KONVENTIONELLES_KREUZHEBEN),
			() -> assertThat(sut.finde(new ID())).isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("erstellen")
	void test04()
	{
		var kraftwert = new Kraftwert(
			new ID(),
			LocalDate.now(),
			new BigDecimal(100),
			ONE_REP_MAX,
			UEBUNG_WETTKAMPFBANKDRUECKEN,
			BENUTZER_JUSTIN);

		sut.speichere(kraftwert);

		assertThat(entityManager.find(Kraftwert.class, kraftwert.getId())).isEqualTo(kraftwert);
	}

	@Test
	@Disabled
	@Transactional
	@DisplayName("aktualisieren")
	void test05()
	{
		var kraftwert = KRAFTWERT_WETTKAMPFBANKDRUECKEN
			.setGewicht(new BigDecimal(105));

		sut.speichere(kraftwert);
		var ergebnis = entityManager.find(Kraftwert.class, kraftwert.getId());

		assertAll(
			() -> assertThat(ergebnis.getId()).isEqualTo(kraftwert.getId()),
			() -> assertThat(ergebnis.getDatum()).isEqualTo(kraftwert.getDatum()),
			() -> assertThat(ergebnis.getGewicht()).isEqualTo(kraftwert.getGewicht()),
			() -> assertThat(ergebnis.getWiederholungen()).isEqualTo(kraftwert.getWiederholungen()),
			() -> assertThat(ergebnis.getUebung()).isEqualTo(kraftwert.getUebung()),
			() -> assertThat(ergebnis.getBenutzer()).isEqualTo(kraftwert.getBenutzer()));
	}

	@Test
	@DisplayName("alle mit Benutzer finden")
	void test06()
	{
		assertAll(
			() -> assertThat(sut.findeAlleMitBenutzer(BENUTZER_JUSTIN.getId())).contains(
				KRAFTWERT_WETTKAMPFBANKDRUECKEN, KRAFTWERT_LOWBAR_KNIEBEUGE, KRAFTWERT_KONVENTIONELLES_KREUZHEBEN),
			() -> assertThat(sut.findeAlleMitBenutzer(BENUTZER_EDUARD.getId())).isEmpty(),
			() -> assertThat(sut.findeAlleMitBenutzer(new ID())).isEmpty());
	}
}
