package de.justinharder.trainharder.domain.repository;

import de.justinharder.trainharder.domain.model.Kraftwert;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import lombok.NonNull;

import java.util.List;

public interface KraftwertRepository extends Repository<Kraftwert>
{
	List<Kraftwert> findeAlleMitBenutzer(@NonNull ID benutzerId);
}
