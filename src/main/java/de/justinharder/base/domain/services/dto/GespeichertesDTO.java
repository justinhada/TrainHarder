package de.justinharder.base.domain.services.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class GespeichertesDTO<T extends GespeichertesDTO<T>> extends DTO<T>
{
	@NonNull
	private final String id;
}
