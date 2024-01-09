package de.justinharder.old.domain.services.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
public class KoerpermasseDto implements Serializable
{
	@Serial
	private static final long serialVersionUID = -7096285691717364927L;

	private String koerpergroesse;
	private String koerpergewicht;
	private String koerperfettAnteil;
	private String fettfreiesKoerpergewicht;
	private String bodyMassIndex;
	private String fatFreeMassIndex;

	public KoerpermasseDto()
	{}

	public KoerpermasseDto(
		@NonNull String koerpergroesse,
		@NonNull String koerpergewicht,
		@NonNull String koerperfettAnteil,
		@NonNull String fettfreiesKoerpergewicht,
		@NonNull String bodyMassIndex,
		@NonNull String fatFreeMassIndex)
	{
		this.koerpergroesse = koerpergroesse;
		this.koerpergewicht = koerpergewicht;
		this.koerperfettAnteil = koerperfettAnteil;
		this.fettfreiesKoerpergewicht = fettfreiesKoerpergewicht;
		this.bodyMassIndex = bodyMassIndex;
		this.fatFreeMassIndex = fatFreeMassIndex;
	}

	public KoerpermasseDto setKoerpergroesse(@NonNull String koerpergroesse)
	{
		this.koerpergroesse = koerpergroesse;
		return this;
	}

	public KoerpermasseDto setKoerpergewicht(@NonNull String koerpergewicht)
	{
		this.koerpergewicht = koerpergewicht;
		return this;
	}

	public KoerpermasseDto setKoerperfettAnteil(@NonNull String koerperfettAnteil)
	{
		this.koerperfettAnteil = koerperfettAnteil;
		return this;
	}

	public KoerpermasseDto setFettfreiesKoerpergewicht(@NonNull String fettfreiesKoerpergewicht)
	{
		this.fettfreiesKoerpergewicht = fettfreiesKoerpergewicht;
		return this;
	}

	public KoerpermasseDto setBodyMassIndex(@NonNull String bodyMassIndex)
	{
		this.bodyMassIndex = bodyMassIndex;
		return this;
	}

	public KoerpermasseDto setFatFreeMassIndex(@NonNull String fatFreeMassIndex)
	{
		this.fatFreeMassIndex = fatFreeMassIndex;
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof KoerpermasseDto))
		{
			return false;
		}
		var that = (KoerpermasseDto) o;
		return Objects.equals(koerpergroesse, that.koerpergroesse)
			&& Objects.equals(koerpergewicht, that.koerpergewicht)
			&& Objects.equals(koerperfettAnteil, that.koerperfettAnteil)
			&& Objects.equals(fettfreiesKoerpergewicht, that.fettfreiesKoerpergewicht)
			&& Objects.equals(bodyMassIndex, that.bodyMassIndex)
			&& Objects.equals(fatFreeMassIndex, that.fatFreeMassIndex);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(koerpergroesse, koerpergewicht, koerperfettAnteil, fettfreiesKoerpergewicht, bodyMassIndex,
			fatFreeMassIndex);
	}
}
