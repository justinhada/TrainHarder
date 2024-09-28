package de.justinharder.base.domain.services.mapping;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.services.dto.GespeichertesDTO;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import lombok.NonNull;

import java.util.List;

public interface Mapping<T extends Entitaet, U extends GespeichertesDTO<U>>
{
	String URL = "http://localhost:8080/%s?page=%s&page_size=%s";

	U mappe(@NonNull T entitaet);

	default PaginationResponse<U> mappe(
		@NonNull PaginationRequest<?> paginationRequest,
		@NonNull List<T> entitaeten,
		@NonNull Integer size,
		@NonNull String endpunkt)
	{
		var page = paginationRequest.getPage();
		var pageSize = paginationRequest.getPageSize();

		PaginationResponse<U> paginationResponse = new PaginationResponse<U>(
			size,
			entitaeten.stream()
				.map(this::mappe)
				.toList())
			.setSelf(URL.formatted(endpunkt, page, pageSize))
			.setFirst(URL.formatted(endpunkt, 1, pageSize));

		int last = size / pageSize;
		if (size % pageSize > 0)
		{
			last++;
		}
		paginationResponse.setLast(URL.formatted(endpunkt, last, pageSize));

		if (last > page)
		{
			paginationResponse.setNext(URL.formatted(endpunkt, page + 1, pageSize));
		}

		return paginationResponse;
	}
}
