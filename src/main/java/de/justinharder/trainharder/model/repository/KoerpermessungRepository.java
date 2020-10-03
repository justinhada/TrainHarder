package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;

import java.util.List;
import java.util.Optional;

public interface KoerpermessungRepository
{
	List<Koerpermessung> ermittleAlleZuBenutzer(final Primaerschluessel benutzerId);

	Optional<Koerpermessung> ermittleZuId(final Primaerschluessel id);

	Koerpermessung speichereKoerpermessung(final Koerpermessung koerpermessung);
}
