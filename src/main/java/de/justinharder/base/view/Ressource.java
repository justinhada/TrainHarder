package de.justinharder.base.view;

import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;

public interface Ressource<T extends GespeichertesDTO<?>, U extends NeuesDTO<?>, V extends AktualisiertesDTO<?>>
{
	Response findeAlle();

	Response finde(@NonNull String id);

	Response erstelle(@NonNull U neuesDto);

	Response aktualisiere(@NonNull String id, @NonNull V aktualisiertesDto);

	Response loesche(@NonNull String id);
}
