package de.justinharder.old.domain.services.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serial;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
public class AuthentifizierungDto extends EntitaetDto
{
	@Serial
	private static final long serialVersionUID = -2585152739995047225L;

	private String mail;
	private String benutzername;

	public AuthentifizierungDto()
	{}

	public AuthentifizierungDto(@NonNull String id, @NonNull String mail, @NonNull String benutzername)
	{
		super(id);
		this.mail = mail;
		this.benutzername = benutzername;
	}

	@Override
	public AuthentifizierungDto setId(@NonNull String id)
	{
		this.id = id;
		return this;
	}

	public AuthentifizierungDto setMail(@NonNull String mail)
	{
		this.mail = mail;
		return this;
	}

	public AuthentifizierungDto setBenutzername(@NonNull String benutzername)
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
		return Objects.equals(id, that.id) && Objects.equals(mail, that.mail) && Objects.equals(benutzername, that.benutzername);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, mail, benutzername);
	}
}
