package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.domain.model.Belastung;
import de.justinharder.trainharder.domain.model.attribute.GrunduebungBelastung;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.attribute.OberkoerperBelastung;
import de.justinharder.trainharder.domain.model.attribute.UnterkoerperBelastung;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("BelastungJpaRepository sollte")
class BelastungJpaRepositoryTest
{
	@Inject
	BelastungJpaRepository sut;

	@Inject
	EntityManager entityManager;

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(BELASTUNG_WETTKAMPFBANKDRUECKEN,
			BELASTUNG_LOWBAR_KNIEBEUGE, BELASTUNG_KONVENTIONELLES_KREUZHEBEN);
	}

	@Test
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(BELASTUNG_WETTKAMPFBANKDRUECKEN.getId())).contains(
				BELASTUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.finde(BELASTUNG_LOWBAR_KNIEBEUGE.getId())).contains(BELASTUNG_LOWBAR_KNIEBEUGE),
			() -> assertThat(sut.finde(BELASTUNG_KONVENTIONELLES_KREUZHEBEN.getId())).contains(
				BELASTUNG_KONVENTIONELLES_KREUZHEBEN),
			() -> assertThat(sut.finde(new ID())).isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("erstellen")
	void test04()
	{
		var belastung = new Belastung(
			new ID(),
			new GrunduebungBelastung(0.0, 1.0, 0.0),
			new OberkoerperBelastung(0.7, 1.0, 0.0, 0.0, 0.0, 0.1),
			new UnterkoerperBelastung(0.0, 0.0, 0.0));

		sut.speichere(belastung);

		assertThat(entityManager.find(Belastung.class, belastung.getId())).isEqualTo(belastung);
	}

	@Test
	@Disabled
	@Transactional
	@DisplayName("aktualisieren")
	void test05()
	{
		var belastung = BELASTUNG_WETTKAMPFBANKDRUECKEN;
		belastung.getOberkoerperBelastung().setTriceps(0.9);

		sut.speichere(belastung);
		var ergebnis = entityManager.find(Belastung.class, belastung.getId());

		assertAll(
			() -> assertThat(ergebnis.getId()).isEqualTo(belastung.getId()),
			() -> assertThat(ergebnis.getGrunduebungBelastung()).isEqualTo(belastung.getGrunduebungBelastung()),
			() -> assertThat(ergebnis.getOberkoerperBelastung()).isEqualTo(belastung.getOberkoerperBelastung()),
			() -> assertThat(ergebnis.getUnterkoerperBelastung()).isEqualTo(belastung.getUnterkoerperBelastung()));
	}
}
