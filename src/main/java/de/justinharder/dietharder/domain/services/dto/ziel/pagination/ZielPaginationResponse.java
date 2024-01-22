package de.justinharder.dietharder.domain.services.dto.ziel.pagination;

import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.dietharder.domain.services.dto.ziel.GespeichertesZiel;
import jakarta.annotation.Nullable;

import java.net.URL;
import java.util.List;

public class ZielPaginationResponse extends PaginationResponse<GespeichertesZiel>
{
	public ZielPaginationResponse(Integer count, List<GespeichertesZiel> results)
	{
		super(count, results);
	}

	@Override
	public ZielPaginationResponse setSelf(@Nullable URL self)
	{
		return (ZielPaginationResponse) super.setSelf(self);
	}

	@Override
	public ZielPaginationResponse setFirst(@Nullable URL first)
	{
		return (ZielPaginationResponse) super.setFirst(first);
	}

	@Override
	public ZielPaginationResponse setPrev(@Nullable URL prev)
	{
		return (ZielPaginationResponse) super.setPrev(prev);
	}

	@Override
	public ZielPaginationResponse setNext(@Nullable URL next)
	{
		return (ZielPaginationResponse) super.setNext(next);
	}

	@Override
	public ZielPaginationResponse setLast(@Nullable URL last)
	{
		return (ZielPaginationResponse) super.setLast(last);
	}
}
