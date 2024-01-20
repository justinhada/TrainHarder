package de.justinharder.dietharder.domain.repository;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.repository.Repository;
import de.justinharder.dietharder.domain.model.Hautfaltendicke;
import lombok.NonNull;

import java.util.List;

public interface HautfaltendickeRepository extends Repository<Hautfaltendicke>
{
	List<Hautfaltendicke> findeAlle(@NonNull ID benutzerId);
}
