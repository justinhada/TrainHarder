package de.justinharder.trainharder.setup;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Entitaet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class TestdatenAnleger
{
	private static final String PERSISTENCE_UNIT_NAME = "hibernate.ejb.persistenceUnitName";

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void legeTestdatenAn()
	{
		try
		{
			speichereTestdaten(entityManager, log::info);
		}
		catch (final Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}

	public void loescheTestdaten(final EntityManager entityManager, final Consumer<String> logger)
	{
		final var persistenceUnit =
			entityManager.getEntityManagerFactory().getProperties().get(PERSISTENCE_UNIT_NAME).toString();
		logger.accept("Beginne mit dem Löschen aller Testdatensätze für PU: " + persistenceUnit);
		final var tabellen = Arrays.asList(
			"Authentifizierung",
			"Belastungsfaktor",
			"Koerpermessung",
			"Kraftwert",
			"Uebung",
			"Benutzer");
		tabellen.forEach(tabelle ->
		{
			logger.accept("Lösche Inhalte der Tabelle \"" + tabelle + "\".");
			final var query = entityManager.createNativeQuery("DELETE FROM " + tabelle);
			query.executeUpdate();
		});
	}

	public void speichereTestdaten(final EntityManager entityManager, final Consumer<String> logger)
	{
		loescheTestdaten(entityManager, logger);
		final var entitaeten = List.of(
			//			Testdaten.KONVENTIONELLES_KREUZHEBEN,
			//			Testdaten.LOWBAR_KNIEBEUGE,
			//			Testdaten.WETTKAMPFBANKDRUECKEN,
			Testdaten.BENUTZER_JUSTIN,
			Testdaten.AUTHENTIFIZIERUNG_JUSTIN,
			Testdaten.BENUTZER_EDUARD,
			Testdaten.AUTHENTIFIZIERUNG_EDUARD);
		//			Testdaten.KOERPERMESSUNG_JUSTIN,
		//			Testdaten.KOERPERMESSUNG_EDUARD,
		//			Testdaten.BELASTUNGSFAKTOR_KONVENTIONELLES_KREUZHEBEN,
		//			Testdaten.BELASTUNGSFAKTOR_LOWBAR_KNIEBEUGE,
		//			Testdaten.BELASTUNGSFAKTOR_WETTKAMPFBANKDRUECKEN,
		//			Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN,
		//			Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
		//			Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN);
		entitaeten.forEach(entitaet -> legeDatensatzAn(entityManager, logger, entitaet));
	}

	private <T extends Entitaet> void legeDatensatzAn(
		final EntityManager entityManager,
		final Consumer<String> logger,
		final T entitaet)
	{
		final var datensatz = entityManager.find(entitaet.getClass(), entitaet.getId());
		if (datensatz == null)
		{
			logger.accept("Lege Datensatz an: " + entitaet);
			entityManager.merge(entitaet);
		}
		else
		{
			logger.accept("Datensatz " + datensatz + " bereits vorhanden.");
		}
	}
}
