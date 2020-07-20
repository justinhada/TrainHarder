package de.justinharder.trainharder.persistence;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.model.repository.UebungRepository;

public class JpaUebungRepository extends JpaRepository<Uebung> implements UebungRepository
{
	private static final long serialVersionUID = 2289966297381182933L;

	@Override
	public List<Uebung> ermittleAlle()
	{
		return super.ermittleAlle(Uebung.class);
	}

	@Override
	public List<Uebung> ermittleZuUebungsart(final Uebungsart uebungsart) throws UebungNichtGefundenException
	{
		try
		{
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Uebung.class);
			final var root = criteriaQuery.from(Uebung.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("uebungsart"), uebungsart));
			return entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch (final NoResultException e)
		{
			throw new UebungNichtGefundenException(
				"Es konnten keine Übungen zur Übungsart \"" + uebungsart.name() + "\" gefunden werden!");
		}
	}

	@Override
	public List<Uebung> ermittleZuUebungskategorie(final Uebungskategorie uebungskategorie)
		throws UebungNichtGefundenException
	{
		try
		{
			final var criteriaBuilder = entityManager.getCriteriaBuilder();
			final var criteriaQuery = criteriaBuilder.createQuery(Uebung.class);
			final var root = criteriaQuery.from(Uebung.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("uebungskategorie"), uebungskategorie));
			return entityManager.createQuery(criteriaQuery).getResultList();
		}
		catch (final NoResultException e)
		{
			throw new UebungNichtGefundenException(
				"Es konnten keine Übungen zur Übungsart \"" + uebungskategorie.name() + "\" gefunden werden!");
		}
	}

	@Override
	public Optional<Uebung> ermittleZuId(final Primaerschluessel id)
	{
		return super.ermittleZuId(Uebung.class, id);
	}

	@Override
	@Transactional
	public Uebung speichereUebung(final Uebung uebung)
	{
		return super.speichereEntitaet(Uebung.class, uebung);
	}
}
