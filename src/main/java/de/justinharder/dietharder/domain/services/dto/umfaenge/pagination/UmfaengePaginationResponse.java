package de.justinharder.dietharder.domain.services.dto.umfaenge.pagination;

import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.dietharder.domain.services.dto.umfaenge.GespeicherteUmfaenge;
import jakarta.annotation.Nullable;

import java.net.URL;
import java.util.List;

public class UmfaengePaginationResponse extends PaginationResponse<GespeicherteUmfaenge>
{
	public UmfaengePaginationResponse(Integer count, List<GespeicherteUmfaenge> results)
	{
		super(count, results);
	}

	@Override
	public UmfaengePaginationResponse setSelf(@Nullable URL self)
	{
		return (UmfaengePaginationResponse) super.setSelf(self);
	}

	@Override
	public UmfaengePaginationResponse setFirst(@Nullable URL first)
	{
		return (UmfaengePaginationResponse) super.setFirst(first);
	}

	@Override
	public UmfaengePaginationResponse setPrev(@Nullable URL prev)
	{
		return (UmfaengePaginationResponse) super.setPrev(prev);
	}

	@Override
	public UmfaengePaginationResponse setNext(@Nullable URL next)
	{
		return (UmfaengePaginationResponse) super.setNext(next);
	}

	@Override
	public UmfaengePaginationResponse setLast(@Nullable URL last)
	{
		return (UmfaengePaginationResponse) super.setLast(last);
	}
}
