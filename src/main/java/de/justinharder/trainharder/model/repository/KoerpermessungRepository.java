package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface KoerpermessungRepository
{
	List<Koerpermessung> ermittleAlleZuBenutzer(@NonNull Primaerschluessel benutzerId);

	Optional<Koerpermessung> ermittleZuId(@NonNull Primaerschluessel id);

	Koerpermessung speichereKoerpermessung(@NonNull Koerpermessung koerpermessung);
}