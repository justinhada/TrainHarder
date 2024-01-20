package de.justinharder.dietharder.persistence;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.persistence.JpaRepository;
import de.justinharder.dietharder.domain.model.Hautfaltendicke;
import de.justinharder.dietharder.domain.repository.HautfaltendickeRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;

import java.util.List;

@Dependent
public class HautfaltendickeJpaRepository extends JpaRepository<Hautfaltendicke> implements HautfaltendickeRepository
{
	public HautfaltendickeJpaRepository()
	{
		super(Hautfaltendicke.class);
	}

	@Override
	public List<Hautfaltendicke> findeAlle(@NonNull ID benutzerId)
	{
		return entityManager.createQuery("""
					SELECT hautfaltendicke
					FROM Hautfaltendicke hautfaltendicke
					WHERE hautfaltendicke.benutzer.id = :benutzerId""",
				Hautfaltendicke.class)
			.setParameter("benutzerId", benutzerId)
			.getResultList();
	}
}
