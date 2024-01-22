package de.justinharder.dietharder.domain.services.dto.hautfaltendicke.pagination;

import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.GespeicherteHautfaltendicke;
import jakarta.annotation.Nullable;

import java.net.URL;
import java.util.List;

public class HautfaltendickePaginationResponse extends PaginationResponse<GespeicherteHautfaltendicke>
{
	public HautfaltendickePaginationResponse(Integer count, List<GespeicherteHautfaltendicke> results)
	{
		super(count, results);
	}

	@Override
	public HautfaltendickePaginationResponse setSelf(@Nullable URL self)
	{
		return (HautfaltendickePaginationResponse) super.setSelf(self);
	}

	@Override
	public HautfaltendickePaginationResponse setFirst(@Nullable URL first)
	{
		return (HautfaltendickePaginationResponse) super.setFirst(first);
	}

	@Override
	public HautfaltendickePaginationResponse setPrev(@Nullable URL prev)
	{
		return (HautfaltendickePaginationResponse) super.setPrev(prev);
	}

	@Override
	public HautfaltendickePaginationResponse setNext(@Nullable URL next)
	{
		return (HautfaltendickePaginationResponse) super.setNext(next);
	}

	@Override
	public HautfaltendickePaginationResponse setLast(@Nullable URL last)
	{
		return (HautfaltendickePaginationResponse) super.setLast(last);
	}
}
