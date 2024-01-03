package de.justinharder.trainharder.domain.services.mapper;

import de.justinharder.trainharder.domain.model.Entitaet;
import de.justinharder.trainharder.domain.services.dto.EntitaetDto;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public interface DtoMapper<T extends Entitaet, U extends EntitaetDto>
{
	default List<U> mappeAlle(@NonNull List<T> entitaeten)
	{
		return entitaeten.stream()
			.map(this::mappe)
			.collect(Collectors.toList());
	}

	U mappe(@NonNull T t);
}