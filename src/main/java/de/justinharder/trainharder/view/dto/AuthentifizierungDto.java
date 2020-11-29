package de.justinharder.trainharder.view.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class AuthentifizierungDto extends Dto
{
	private static final long serialVersionUID = -2585152739995047225L;

	private String mail;
	private String benutzername;

	public AuthentifizierungDto()
	{}

	public AuthentifizierungDto(String primaerschluessel, String mail, String benutzername)
	{
		super(primaerschluessel);
		this.mail = mail;
		this.benutzername = benutzername;
	}

	public AuthentifizierungDto setMail(String mail)
	{
		this.mail = mail;
		return this;
	}

	public AuthentifizierungDto setBenutzername(String benutzername)
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
		if (!(o instanceof AuthentifizierungDto))
		{
			return false;
		}
		var that = (AuthentifizierungDto) o;
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
