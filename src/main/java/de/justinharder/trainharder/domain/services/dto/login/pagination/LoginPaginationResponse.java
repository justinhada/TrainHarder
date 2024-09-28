package de.justinharder.trainharder.domain.services.dto.login.pagination;

import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.trainharder.domain.services.dto.login.GespeicherterLogin;
import jakarta.annotation.Nullable;

import java.net.URL;
import java.util.List;

public class LoginPaginationResponse extends PaginationResponse<GespeicherterLogin>
{
	public LoginPaginationResponse(Integer count, List<GespeicherterLogin> results)
	{
		super(count, results);
	}

	@Override
	public LoginPaginationResponse setSelf(@Nullable URL self)
	{
		return (LoginPaginationResponse) super.setSelf(self);
	}

	@Override
	public LoginPaginationResponse setFirst(@Nullable URL first)
	{
		return (LoginPaginationResponse) super.setFirst(first);
	}

	@Override
	public LoginPaginationResponse setPrev(@Nullable URL prev)
	{
		return (LoginPaginationResponse) super.setPrev(prev);
	}

	@Override
	public LoginPaginationResponse setNext(@Nullable URL next)
	{
		return (LoginPaginationResponse) super.setNext(next);
	}

	@Override
	public LoginPaginationResponse setLast(@Nullable URL last)
	{
		return (LoginPaginationResponse) super.setLast(last);
	}
}
