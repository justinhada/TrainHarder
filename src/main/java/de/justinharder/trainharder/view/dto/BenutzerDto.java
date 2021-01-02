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
public class BenutzerDto extends Dto
{
	private static final long serialVersionUID = 2341943957236551490L;

	private String vorname;
	private String nachname;
	private LocalDate geburtsdatum;
	private String kraftlevel;
	private String geschlecht;
	private String erfahrung;
	private String ernaehrung;
	private String schlafqualitaet;
	private String stress;
	private String doping;
	private String regenerationsfaehigkeit;
	private AuthentifizierungDto authentifizierung;
	@Setter(value = AccessLevel.NONE)
	private List<KoerpermessungDto> koerpermessungen = new ArrayList<>();

	public BenutzerDto()
	{}

	public BenutzerDto(
		@NonNull String primaerschluessel,
		@NonNull String vorname,
		@NonNull String nachname,
		@NonNull LocalDate geburtsdatum,
		@NonNull String kraftlevel,
		@NonNull String geschlecht,
		@NonNull String erfahrung,
		@NonNull String ernaehrung,
		@NonNull String schlafqualitaet,
		@NonNull String stress,
		@NonNull String doping,
		@NonNull String regenerationsfaehigkeit,
		@NonNull AuthentifizierungDto authentifizierung,
		@NonNull List<KoerpermessungDto> koerpermessungen)
	{
		super(primaerschluessel);
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.kraftlevel = kraftlevel;
		this.geschlecht = geschlecht;
		this.erfahrung = erfahrung;
		this.ernaehrung = ernaehrung;
		this.schlafqualitaet = schlafqualitaet;
		this.stress = stress;
		this.doping = doping;
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
		this.authentifizierung = authentifizierung;
		this.koerpermessungen = sortiereKoerpermessungen(koerpermessungen);
	}

	@Override
	public BenutzerDto setPrimaerschluessel(@NonNull String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public BenutzerDto setVorname(@NonNull String vorname)
	{
		this.vorname = vorname;
		return this;
	}

	public BenutzerDto setNachname(@NonNull String nachname)
	{
		this.nachname = nachname;
		return this;
	}

	public BenutzerDto setGeburtsdatum(@NonNull LocalDate geburtsdatum)
	{
		this.geburtsdatum = geburtsdatum;
		return this;
	}

	public BenutzerDto setKraftlevel(@NonNull String kraftlevel)
	{
		this.kraftlevel = kraftlevel;
		return this;
	}

	public BenutzerDto setGeschlecht(@NonNull String geschlecht)
	{
		this.geschlecht = geschlecht;
		return this;
	}

	public BenutzerDto setErfahrung(@NonNull String erfahrung)
	{
		this.erfahrung = erfahrung;
		return this;
	}

	public BenutzerDto setErnaehrung(@NonNull String ernaehrung)
	{
		this.ernaehrung = ernaehrung;
		return this;
	}

	public BenutzerDto setSchlafqualitaet(@NonNull String schlafqualitaet)
	{
		this.schlafqualitaet = schlafqualitaet;
		return this;
	}

	public BenutzerDto setStress(@NonNull String stress)
	{
		this.stress = stress;
		return this;
	}

	public BenutzerDto setDoping(@NonNull String doping)
	{
		this.doping = doping;
		return this;
	}

	public BenutzerDto setRegenerationsfaehigkeit(@NonNull String regenerationsfaehigkeit)
	{
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
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
		BenutzerDto that = (BenutzerDto) o;
		return Objects.equals(primaerschluessel, that.primaerschluessel)
			&& Objects.equals(vorname, that.vorname)
			&& Objects.equals(nachname, that.nachname)
			&& Objects.equals(geburtsdatum, that.geburtsdatum)
			&& Objects.equals(kraftlevel, that.kraftlevel)
			&& Objects.equals(geschlecht, that.geschlecht)
			&& Objects.equals(erfahrung, that.erfahrung)
			&& Objects.equals(ernaehrung, that.ernaehrung)
			&& Objects.equals(schlafqualitaet, that.schlafqualitaet)
			&& Objects.equals(stress, that.stress)
			&& Objects.equals(doping, that.doping)
			&& Objects.equals(regenerationsfaehigkeit, that.regenerationsfaehigkeit)
			&& Objects.equals(authentifizierung, that.authentifizierung)
			&& Objects.equals(koerpermessungen, that.koerpermessungen);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel, vorname, nachname, geburtsdatum, kraftlevel, geschlecht, erfahrung,
			ernaehrung, schlafqualitaet, stress, doping, regenerationsfaehigkeit, authentifizierung, koerpermessungen);
	}
}
