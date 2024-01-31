package de.justinharder.dietharder.domain.repository;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.repository.Repository;
import de.justinharder.dietharder.domain.model.Umfaenge;
import lombok.NonNull;

import java.util.List;

public interface UmfaengeRepository extends Repository<Umfaenge>
{
	List<Umfaenge> findeAlle(@NonNull ID benutzerId, @NonNull Integer page, @NonNull Integer pageSize);
}
