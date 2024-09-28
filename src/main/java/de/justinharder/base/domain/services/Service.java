package de.justinharder.base.domain.services;

import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import de.justinharder.base.domain.services.dto.GeloeschtesDTO;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import lombok.NonNull;

public interface Service<
	T extends GespeichertesDTO<T>,
	U extends NeuesDTO<U>,
	V extends AktualisiertesDTO<V>,
	W extends GeloeschtesDTO<W>>
{
	PaginationResponse<T> findeAlle(@NonNull PaginationRequest<T> paginationRequest);

	T finde(@NonNull String id) throws Exception;

	T erstelle(@NonNull U neuesDto) throws Exception;

	T aktualisiere(@NonNull String id, @NonNull V aktualisiertesDto) throws Exception;

	W loesche(@NonNull String id) throws Exception;
}
