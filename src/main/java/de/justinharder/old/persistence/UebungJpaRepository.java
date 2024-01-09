package de.justinharder.old.persistence;

import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.old.domain.repository.UebungRepository;
import de.justinharder.old.domain.model.Uebung;
import de.justinharder.old.domain.model.enums.Uebungsart;
import de.justinharder.old.domain.model.enums.Uebungskategorie;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class UebungJpaRepository extends JpaRepository<Uebung> implements UebungRepository
{
	public UebungJpaRepository()
	{
		super(Uebung.class);
	}

	@Override
	public List<Uebung> findeAlleMitUebungsart(@NonNull Uebungsart uebungsart)
	{
		return entityManager.createQuery("""
					SELECT uebung
					FROM Uebung uebung
					WHERE uebung.uebungsart = :uebungsart""",
			Uebung.class)
			.setParameter("uebungsart", uebungsart)
			.getResultList();
	}

	@Override
	public List<Uebung> findeAlleMitUebungskategorie(@NonNull Uebungskategorie uebungskategorie)
	{
		return entityManager.createQuery("""
					SELECT uebung
					FROM Uebung uebung
					WHERE uebung.uebungskategorie = :uebungskategorie""",
				Uebung.class)
			.setParameter("uebungskategorie", uebungskategorie)
			.getResultList();
	}
}
