package de.justinharder.base.view;

import de.justinharder.base.domain.services.dto.DTO;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import jakarta.ws.rs.core.Response;

public interface Ressource<T extends GespeichertesDTO<?>, U extends DTO<?>, V extends DTO<?>>
{
	Response findeAlle();

	Response finde(String id);

	Response erstelle(U neuesDto);

	Response aktualisiere(String id, V aktualisiertesDto);

	Response loesche(String id);
}
