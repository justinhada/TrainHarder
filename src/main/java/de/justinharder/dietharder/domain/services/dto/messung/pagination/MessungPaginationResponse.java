package de.justinharder.dietharder.domain.services.dto.messung.pagination;

import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.dietharder.domain.services.dto.messung.GespeicherteMessung;
import jakarta.annotation.Nullable;

import java.net.URL;
import java.util.List;

public class MessungPaginationResponse extends PaginationResponse<GespeicherteMessung>
{
	public MessungPaginationResponse(Integer count, List<GespeicherteMessung> results)
	{
		super(count, results);
	}

	@Override
	public MessungPaginationResponse setSelf(@Nullable URL self)
	{
		return (MessungPaginationResponse) super.setSelf(self);
	}

	@Override
	public MessungPaginationResponse setFirst(@Nullable URL first)
	{
		return (MessungPaginationResponse) super.setFirst(first);
	}

	@Override
	public MessungPaginationResponse setPrev(@Nullable URL prev)
	{
		return (MessungPaginationResponse) super.setPrev(prev);
	}

	@Override
	public MessungPaginationResponse setNext(@Nullable URL next)
	{
		return (MessungPaginationResponse) super.setNext(next);
	}

	@Override
	public MessungPaginationResponse setLast(@Nullable URL last)
	{
		return (MessungPaginationResponse) super.setLast(last);
	}
}
