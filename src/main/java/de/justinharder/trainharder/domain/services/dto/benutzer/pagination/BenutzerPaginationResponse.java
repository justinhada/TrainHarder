package de.justinharder.trainharder.domain.services.dto.benutzer.pagination;

import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.trainharder.domain.services.dto.benutzer.GespeicherterBenutzer;
import jakarta.annotation.Nullable;
import lombok.ToString;

import java.net.URL;
import java.util.List;

@ToString(callSuper = true)
public class BenutzerPaginationResponse extends PaginationResponse<GespeicherterBenutzer>
{
	public BenutzerPaginationResponse(Integer count, List<GespeicherterBenutzer> results)
	{
		super(count, results);
	}

	@Override
	public BenutzerPaginationResponse setSelf(@Nullable URL self)
	{
		return (BenutzerPaginationResponse) super.setSelf(self);
	}

	@Override
	public BenutzerPaginationResponse setFirst(@Nullable URL first)
	{
		return (BenutzerPaginationResponse) super.setFirst(first);
	}

	@Override
	public BenutzerPaginationResponse setPrev(@Nullable URL prev)
	{
		return (BenutzerPaginationResponse) super.setPrev(prev);
	}

	@Override
	public BenutzerPaginationResponse setNext(@Nullable URL next)
	{
		return (BenutzerPaginationResponse) super.setNext(next);
	}

	@Override
	public BenutzerPaginationResponse setLast(@Nullable URL last)
	{
		return (BenutzerPaginationResponse) super.setLast(last);
	}
}
