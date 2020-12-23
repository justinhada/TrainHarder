package de.justinharder.trainharder.model.services.mapper;

import de.justinharder.trainharder.model.domain.Entitaet;
import de.justinharder.trainharder.view.dto.Dto;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public interface DtoMapper<T extends Entitaet, U extends Dto>
{
	default List<U> mappeAlle(@NonNull List<T> entitaeten)
	{
		return entitaeten.stream()
			.map(this::mappe)
			.collect(Collectors.toList());
	}

	U mappe(@NonNull T t);
}
