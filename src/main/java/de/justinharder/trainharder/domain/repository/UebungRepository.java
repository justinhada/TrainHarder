package de.justinharder.trainharder.domain.repository;

import de.justinharder.base.domain.repository.Repository;
import de.justinharder.trainharder.domain.model.Uebung;
import de.justinharder.trainharder.domain.model.enums.Uebungsart;
import de.justinharder.trainharder.domain.model.enums.Uebungskategorie;
import lombok.NonNull;

import java.util.List;

public interface UebungRepository extends Repository<Uebung>
{
	List<Uebung> findeAlleMitUebungsart(@NonNull Uebungsart uebungsart);

	List<Uebung> findeAlleMitUebungskategorie(@NonNull Uebungskategorie uebungskategorie);
}
