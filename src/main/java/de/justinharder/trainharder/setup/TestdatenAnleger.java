package de.justinharder.trainharder.setup;

import de.justinharder.trainharder.model.domain.*;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@ApplicationScoped
public class TestdatenAnleger
{
	private static final String PERSISTENCE_UNIT_NAME = "hibernate.ejb.persistenceUnitName";

	public void loescheTestdaten(EntityManager entityManager, Consumer<String> logger)
	{
		var persistenceUnit =
			entityManager.getEntityManagerFactory().getProperties().get(PERSISTENCE_UNIT_NAME).toString();
		logger.accept("Beginne mit dem Löschen aller Testdatensätze für PU: " + persistenceUnit);
		List.of(
			Authentifizierung.class,
			Belastungsfaktor.class,
			Koerpermessung.class,
			Kraftwert.class,
			Uebung.class,
			Benutzer.class)
			.forEach(tabelle ->
			{
				logger.accept("Lösche Inhalte der Tabelle \"" + tabelle + "\".");
				var query = "DELETE FROM ".concat(tabelle.getSimpleName());
				entityManager.createNativeQuery(query, tabelle).executeUpdate();
			});
	}

	public void speichereTestdaten(EntityManager entityManager, Consumer<String> logger)
	{
		loescheTestdaten(entityManager, logger);
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
