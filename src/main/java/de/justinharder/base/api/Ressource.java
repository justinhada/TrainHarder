package de.justinharder.base.api;

import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import de.justinharder.base.domain.services.dto.GeloeschtesDTO;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;

public interface Ressource<
	T extends GespeichertesDTO<T>,
	U extends NeuesDTO<U>,
	V extends AktualisiertesDTO<V>,
	W extends GeloeschtesDTO<W>>
{
	Response findeAlle(@NonNull PaginationRequest<T> paginationRequest);

	Response finde(@NonNull String id);

	Response erstelle(@NonNull U neuesDto);

	Response aktualisiere(@NonNull String id, @NonNull V aktualisiertesDto);

	Response loesche(@NonNull String id);
}
