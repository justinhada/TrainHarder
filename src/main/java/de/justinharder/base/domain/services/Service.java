package de.justinharder.base.domain.services;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import lombok.NonNull;

import java.util.List;

public interface Service<T extends Entitaet, U extends GespeichertesDTO<?>, V extends NeuesDTO<?>, W extends AktualisiertesDTO<?>, X extends PaginationRequest<?>, Y extends PaginationResponse<?>>
{
	List<U> findeAlle();

	Y findeAlle(@NonNull X paginationRequest);

	U finde(@NonNull String id) throws Exception;

	U erstelle(@NonNull V neuesDto) throws Exception;

	U aktualisiere(@NonNull String id, @NonNull W aktualisiertesDto) throws Exception;

	void loesche(@NonNull String id) throws Exception;
}
