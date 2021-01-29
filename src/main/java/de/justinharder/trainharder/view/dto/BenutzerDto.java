package de.justinharder.trainharder.view.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@ToString(callSuper = true)
public class BenutzerDto extends EntitaetDto
{
	private static final long serialVersionUID = 2341943957236551490L;

	private NameDto name;
	private LocalDate geburtsdatum;
	private BenutzerangabeDto benutzerangabe;
	private AuthentifizierungDto authentifizierung;
	private List<KoerpermessungDto> koerpermessungen = new ArrayList<>();

	public BenutzerDto()
	{}

	public BenutzerDto(
		@NonNull String primaerschluessel,
		@NonNull NameDto name,
		@NonNull LocalDate geburtsdatum,
		@NonNull BenutzerangabeDto benutzerangabe,
		@NonNull AuthentifizierungDto authentifizierung,
		@NonNull List<KoerpermessungDto> koerpermessungen)
	{
		super(primaerschluessel);
		this.name = name;
		this.geburtsdatum = geburtsdatum;
		this.benutzerangabe = benutzerangabe;
		this.authentifizierung = authentifizierung;
		this.koerpermessungen = sortiereKoerpermessungen(koerpermessungen);
	}

	public String getKoerpergewicht()
	{
		return koerpermessungen.get(koerpermessungen.size() - 1).getKoerpermasse().getKoerpergewicht();
	}

	@Override
	public BenutzerDto setPrimaerschluessel(@NonNull String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public BenutzerDto setName(@NonNull NameDto name)
	{
		this.name = name;
		return this;
	}

	public BenutzerDto setGeburtsdatum(@NonNull LocalDate geburtsdatum)
	{
		this.geburtsdatum = geburtsdatum;
		return this;
	}

	public BenutzerDto setBenutzerangabe(@NonNull BenutzerangabeDto benutzerangabe)
	{
		this.benutzerangabe = benutzerangabe;
		return this;
	}

	public BenutzerDto setAuthentifizierung(@NonNull AuthentifizierungDto authentifizierung)
	{
		this.authentifizierung = authentifizierung;
		return this;
	}

	public BenutzerDto fuegeKoerpermessungHinzu(@NonNull KoerpermessungDto koerpermessung)
	{
		koerpermessungen.add(koerpermessung);
		koerpermessungen = sortiereKoerpermessungen(koerpermessungen);
		return this;
	}

	private List<KoerpermessungDto> sortiereKoerpermessungen(List<KoerpermessungDto> koerpermessungen)
	{
		return koerpermessungen.stream()
			.sorted(Comparator.comparing(KoerpermessungDto::getDatum))
			.collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof BenutzerDto))
		{
			return false;
		}
		var benutzerDto = (BenutzerDto) o;
		return Objects.equals(primaerschluessel, benutzerDto.primaerschluessel)
			&& Objects.equals(name, benutzerDto.name)
			&& Objects.equals(geburtsdatum, benutzerDto.geburtsdatum)
			&& Objects.equals(benutzerangabe, benutzerDto.benutzerangabe)
			&& Objects.equals(authentifizierung, benutzerDto.authentifizierung)
			&& Objects.equals(koerpermessungen, benutzerDto.koerpermessungen);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel, name, geburtsdatum, benutzerangabe, authentifizierung, koerpermessungen);
	}
}
