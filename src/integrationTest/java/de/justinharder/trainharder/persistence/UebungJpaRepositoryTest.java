package de.justinharder.trainharder.persistence;

import de.justinharder.trainharder.domain.model.Belastung;
import de.justinharder.trainharder.domain.model.Uebung;
import de.justinharder.trainharder.domain.model.embeddables.GrunduebungBelastung;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.embeddables.OberkoerperBelastung;
import de.justinharder.trainharder.domain.model.embeddables.UnterkoerperBelastung;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.trainharder.domain.model.enums.Uebungsart.*;
import static de.justinharder.trainharder.domain.model.enums.Uebungskategorie.*;
import static de.justinharder.trainharder.setup.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("UebungJpaRepository sollte")
class UebungJpaRepositoryTest
{
	@Inject
	UebungJpaRepository sut;

	@Inject
	EntityManager entityManager;

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.findeAlleMitUebungsart(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeAlleMitUebungskategorie(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle()).containsExactlyInAnyOrder(UEBUNG_WETTKAMPFBANKDRUECKEN, UEBUNG_LOWBAR_KNIEBEUGE,
			UEBUNG_KONVENTIONELLES_KREUZHEBEN);
	}

	@Test
	@DisplayName("finden")
	void test03()
	{
		assertAll(
			() -> assertThat(sut.finde(UEBUNG_WETTKAMPFBANKDRUECKEN.getId())).contains(
				UEBUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.finde(UEBUNG_LOWBAR_KNIEBEUGE.getId())).contains(UEBUNG_LOWBAR_KNIEBEUGE),
			() -> assertThat(sut.finde(UEBUNG_KONVENTIONELLES_KREUZHEBEN.getId())).contains(
				UEBUNG_KONVENTIONELLES_KREUZHEBEN),
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
		entityManager.persist(belastung);

		var uebung = new Uebung(
			new ID(),
			"Spoto Bankdrücken",
			GRUNDUEBUNG,
			WETTKAMPF_BANKDRUECKEN,
			belastung);

		sut.speichere(uebung);

		assertThat(entityManager.find(Uebung.class, uebung.getId())).isEqualTo(uebung);
	}

	@Test
	@Disabled
	@Transactional
	@DisplayName("aktualisieren")
	void test05()
	{
		var uebung = UEBUNG_WETTKAMPFBANKDRUECKEN
			.setBezeichnung("Wettkampfbankdrücken (2s pausiert)");

		sut.speichere(uebung);
		var ergebnis = entityManager.find(Uebung.class, uebung.getId());

		assertAll(
			() -> assertThat(ergebnis.getId()).isEqualTo(uebung.getId()),
			() -> assertThat(ergebnis.getBezeichnung()).isEqualTo(uebung.getBezeichnung()),
			() -> assertThat(ergebnis.getUebungsart()).isEqualTo(uebung.getUebungsart()),
			() -> assertThat(ergebnis.getUebungskategorie()).isEqualTo(uebung.getUebungskategorie()),
			() -> assertThat(ergebnis.getBelastung()).isEqualTo(uebung.getBelastung()));
	}

	@Test
	@DisplayName("alle mit Uebungsart finden")
	void test06()
	{
		assertAll(
			() -> assertThat(sut.findeAlleMitUebungsart(GRUNDUEBUNG)).contains(
				UEBUNG_WETTKAMPFBANKDRUECKEN, UEBUNG_LOWBAR_KNIEBEUGE, UEBUNG_KONVENTIONELLES_KREUZHEBEN),
			() -> assertThat(sut.findeAlleMitUebungsart(GRUNDUEBUNG_VARIATION)).isEmpty(),
			() -> assertThat(sut.findeAlleMitUebungsart(ASSISTENZ)).isEmpty());
	}

	@Test
	@DisplayName("alle mit Übungskategorie finden")
	void test07()
	{
		assertAll(
			() -> assertThat(sut.findeAlleMitUebungskategorie(WETTKAMPF_BANKDRUECKEN)).contains(
				UEBUNG_WETTKAMPFBANKDRUECKEN),
			() -> assertThat(sut.findeAlleMitUebungskategorie(WETTKAMPF_KNIEBEUGE)).containsExactlyInAnyOrder(
				UEBUNG_LOWBAR_KNIEBEUGE),
			() -> assertThat(sut.findeAlleMitUebungskategorie(WETTKAMPF_KREUZHEBEN)).containsExactlyInAnyOrder(
				UEBUNG_KONVENTIONELLES_KREUZHEBEN),
			() -> assertThat(sut.findeAlleMitUebungskategorie(UEBERKOPFDRUECKEN)).isEmpty());
	}
}
