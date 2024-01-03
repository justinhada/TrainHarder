package de.justinharder.trainharder.domain.repository;

import de.justinharder.trainharder.domain.model.Koerpermessung;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import lombok.NonNull;

import java.util.List;

public interface KoerpermessungRepository extends Repository<Koerpermessung>
{
	List<Koerpermessung> findeAlleMitBenutzer(@NonNull ID benutzerId);
}
