package de.justinharder.trainharder.domain.repository;

import de.justinharder.base.domain.repository.Repository;
import de.justinharder.trainharder.domain.model.Koerpermessung;
import de.justinharder.base.domain.model.attribute.ID;
import lombok.NonNull;

import java.util.List;

public interface KoerpermessungRepository extends Repository<Koerpermessung>
{
	List<Koerpermessung> findeAlleMitBenutzer(@NonNull ID benutzerId);
}
