package de.justinharder.trainharder.model.domain.embeddables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Embeddable
public class Name implements Serializable
{
	private static final long serialVersionUID = 5815817146290998651L;

	@Column(name = "Vorname")
	private String vorname;
	@Column(name = "Nachname")
	private String nachname;

	public Name()
	{}

	public Name(final String vorname, final String nachname)
	{
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public Name setVorname(final String vorname)
	{
		this.vorname = vorname;
		return this;
	}

	public Name setNachname(final String nachname)
	{
		this.nachname = nachname;
		return this;
	}
}




