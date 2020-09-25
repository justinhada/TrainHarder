package de.justinharder.trainharder.view.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
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
}
