package de.justinharder.trainharder.domain.repository;

import de.justinharder.trainharder.domain.model.Uebung;
import de.justinharder.trainharder.domain.model.embeddables.Primaerschluessel;
import de.justinharder.trainharder.domain.model.enums.Uebungsart;
import de.justinharder.trainharder.domain.model.enums.Uebungskategorie;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface UebungRepository
{
	List<Uebung> ermittleAlle();

	List<Uebung> ermittleAlleZuUebungsart(@NonNull Uebungsart uebungsart);

	List<Uebung> ermittleAlleZuUebungskategorie(@NonNull Uebungskategorie uebungskategorie);

	Optional<Uebung> ermittleZuId(@NonNull Primaerschluessel id);

	Uebung speichereUebung(@NonNull Uebung uebung);
}
