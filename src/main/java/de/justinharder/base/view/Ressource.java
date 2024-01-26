package de.justinharder.base.view;

import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;

public interface Ressource<T extends GespeichertesDTO<?>, U extends NeuesDTO<?>, V extends AktualisiertesDTO<?>, W extends PaginationRequest<?>, X extends PaginationResponse<?>>
{
	Response findeAlle(@NonNull W paginationRequest);

	Response finde(@NonNull String id);

	Response erstelle(@NonNull U neuesDto);

	Response aktualisiere(@NonNull String id, @NonNull V aktualisiertesDto);

	Response loesche(@NonNull String id);
}
