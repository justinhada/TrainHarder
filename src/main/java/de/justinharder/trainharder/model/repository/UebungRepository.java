package de.justinharder.trainharder.model.repository;

import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
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
