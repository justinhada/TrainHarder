package de.justinharder.base.domain.services;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import lombok.NonNull;

import java.util.List;

public interface Service<T extends Entitaet, U extends GespeichertesDTO<?>, V extends NeuesDTO<?>, W extends AktualisiertesDTO<?>>
{
	List<U> findeAlle();

	U finde(@NonNull String id);

	U erstelle(@NonNull V neuesDto);

	U aktualisiere(@NonNull String id, @NonNull W aktualisiertesDto);

	void loesche(@NonNull String id);
}
