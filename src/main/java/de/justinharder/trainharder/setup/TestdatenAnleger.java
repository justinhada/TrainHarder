package de.justinharder.trainharder.setup;

import de.justinharder.trainharder.model.domain.Entitaet;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

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
			entityManager.createNativeQuery("DELETE FROM " + tabelle)
				.executeUpdate();
		});
	}

	public void speichereTestdaten(final EntityManager entityManager, final Consumer<String> logger)
	{
		loescheTestdaten(entityManager, logger);
		List.of(
			Testdaten.BENUTZER_JUSTIN,
			Testdaten.BENUTZER_EDUARD,
			Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
			Testdaten.KRAFTWERT_WETTKAMPFBANKDRUECKEN,
			Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
			Testdaten.KRAFTWERT_LOWBAR_KNIEBEUGE,
			Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN,
			Testdaten.KRAFTWERT_KONVENTIONELLES_KREUZHEBEN)
			.forEach(entitaet -> legeDatensatzAn(entityManager, logger, entitaet));
	}

	private <T extends Entitaet> void legeDatensatzAn(
		final EntityManager entityManager,
		final Consumer<String> logger,
		final T entitaet)
	{
		final var datensatz = entityManager.find(entitaet.getClass(), entitaet.getPrimaerschluessel());
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
