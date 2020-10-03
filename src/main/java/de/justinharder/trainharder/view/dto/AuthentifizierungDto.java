package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
public class AuthentifizierungDto implements Serializable
{
	private static final long serialVersionUID = -2585152739995047225L;

	private String primaerschluessel;
	private String mail;
	private String benutzername;

	public AuthentifizierungDto()
	{}

	public AuthentifizierungDto(final String primaerschluessel, final String mail, final String benutzername)
	{
		this.primaerschluessel = primaerschluessel;
		this.mail = mail;
		this.benutzername = benutzername;
	}

	public AuthentifizierungDto setPrimaerschluessel(final String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public AuthentifizierungDto setMail(final String mail)
	{
		this.mail = mail;
		return this;
	}

	public AuthentifizierungDto setBenutzername(final String benutzername)
	{
		this.benutzername = benutzername;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		AuthentifizierungDto that = (AuthentifizierungDto) o;
		return primaerschluessel.equals(that.primaerschluessel) &&
			mail.equals(that.mail) &&
			benutzername.equals(that.benutzername);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel, mail, benutzername);
	}
}
