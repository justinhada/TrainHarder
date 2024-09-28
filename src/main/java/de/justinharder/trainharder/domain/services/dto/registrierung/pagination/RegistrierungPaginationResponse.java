package de.justinharder.trainharder.domain.services.dto.registrierung.pagination;

import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.trainharder.domain.services.dto.registrierung.GespeicherteRegistrierung;
import jakarta.annotation.Nullable;

import java.net.URL;
import java.util.List;

public class RegistrierungPaginationResponse extends PaginationResponse<GespeicherteRegistrierung>
{
	public RegistrierungPaginationResponse(Integer count, List<GespeicherteRegistrierung> results)
	{
		super(count, results);
	}

	@Override
	public RegistrierungPaginationResponse setSelf(@Nullable URL self)
	{
		return (RegistrierungPaginationResponse) super.setSelf(self);
	}

	@Override
	public RegistrierungPaginationResponse setFirst(@Nullable URL first)
	{
		return (RegistrierungPaginationResponse) super.setFirst(first);
	}

	@Override
	public RegistrierungPaginationResponse setPrev(@Nullable URL prev)
	{
		return (RegistrierungPaginationResponse) super.setPrev(prev);
	}

	@Override
	public RegistrierungPaginationResponse setNext(@Nullable URL next)
	{
		return (RegistrierungPaginationResponse) super.setNext(next);
	}

	@Override
	public RegistrierungPaginationResponse setLast(@Nullable URL last)
	{
		return (RegistrierungPaginationResponse) super.setLast(last);
	}
}
