package de.justinharder.old.domain.repository;

import de.justinharder.base.domain.repository.Repository;
import de.justinharder.old.domain.model.Koerpermessung;
import de.justinharder.base.domain.model.attribute.ID;
import lombok.NonNull;

import java.util.List;

public interface KoerpermessungRepository extends Repository<Koerpermessung>
{
	List<Koerpermessung> findeAlleMitBenutzer(@NonNull ID benutzerId);
}
