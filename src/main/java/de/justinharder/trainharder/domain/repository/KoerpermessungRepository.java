package de.justinharder.trainharder.domain.repository;

import de.justinharder.trainharder.domain.model.Koerpermessung;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface KoerpermessungRepository
{
	List<Koerpermessung> ermittleAlleZuBenutzer(@NonNull Primaerschluessel benutzerId);

	Optional<Koerpermessung> ermittleZuId(@NonNull Primaerschluessel id);

	Koerpermessung speichereKoerpermessung(@NonNull Koerpermessung koerpermessung);
}
