package de.justinharder.base.domain.services;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import de.justinharder.base.domain.services.dto.NeuesDTO;

import java.util.List;

public interface Service<T extends Entitaet, U extends GespeichertesDTO<?>, V extends NeuesDTO<?>, W extends AktualisiertesDTO<?>>
{
	List<U> findeAlle();

	U finde(String id);

	U erstelle(V neuesDto);

	U aktualisiere(String id, W aktualisiertesDto);

	void loesche(String id);
}
