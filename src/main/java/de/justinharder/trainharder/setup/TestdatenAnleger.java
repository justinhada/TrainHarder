package de.justinharder.trainharder.setup;

import de.justinharder.trainharder.model.domain.*;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@ApplicationScoped
public class TestdatenAnleger
{
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void legeTestdatenAn()
	{
		speichereTestdaten(entityManager, log::info);
	}

	public void speichereTestdaten(EntityManager entityManager, Consumer<String> logger)
	{
		List.of(
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN,
			Testdaten.AUTHENTIFIZIERUNG_EDUARD,
			Testdaten.BENUTZER_JUSTIN,
			Testdaten.BENUTZER_EDUARD,
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN,
			Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN)
			.forEach(entitaet -> legeDatensatzAn(entityManager, logger, entitaet));
	}

	private <T extends Entitaet> void legeDatensatzAn(EntityManager entityManager, Consumer<String> logger, T entitaet)
	{
		logger.accept("Weiter mit Datensatz " + entitaet);
		var datensatz = entityManager.find(entitaet.getClass(), entitaet.getPrimaerschluessel());
		if (datensatz == null)
		{
			logger.accept("Lege Datensatz an: " + entitaet);
			entityManager.persist(entitaet);
		}
		else
		{
			logger.accept("Datensatz " + datensatz + " bereits vorhanden.");
		}
	}
}
