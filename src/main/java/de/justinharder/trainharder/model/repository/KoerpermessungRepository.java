package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

import java.util.List;
import java.util.Optional;

public interface KoerpermessungRepository
{
	List<Koerpermessung> ermittleAlleZuBenutzer(Primaerschluessel benutzerId);

	Optional<Koerpermessung> ermittleZuId(Primaerschluessel id);

	Koerpermessung speichereKoerpermessung(Koerpermessung koerpermessung);
}
