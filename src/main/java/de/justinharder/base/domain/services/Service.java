package de.justinharder.base.domain.services;

import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import de.justinharder.base.domain.services.dto.GeloeschtesDTO;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import lombok.NonNull;

import java.util.List;

public interface Service<T extends GespeichertesDTO<?>, U extends NeuesDTO<?>, V extends AktualisiertesDTO<?>, W extends GeloeschtesDTO<?>, X extends PaginationRequest<?>, Y extends PaginationResponse<?>>
{
	List<T> findeAlle();

	Y findeAlle(@NonNull X paginationRequest);

	T finde(@NonNull String id) throws Exception;

	T erstelle(@NonNull U neuesDto) throws Exception;

	T aktualisiere(@NonNull String id, @NonNull V aktualisiertesDto) throws Exception;

	W loesche(@NonNull String id) throws Exception;
}
