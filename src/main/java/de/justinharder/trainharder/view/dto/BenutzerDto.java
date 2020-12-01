package de.justinharder.trainharder.view.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
		String primaerschluessel,
		String vorname,
		String nachname,
		LocalDate geburtsdatum,
		String kraftlevel,
		String geschlecht,
		String erfahrung,
		String ernaehrung,
		String schlafqualitaet,
		String stress,
		String doping,
		String regenerationsfaehigkeit,
		AuthentifizierungDto authentifizierung,
		List<KoerpermessungDto> koerpermessungen)
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
	public BenutzerDto setPrimaerschluessel(String primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public BenutzerDto setVorname(String vorname)
	{
		this.vorname = vorname;
		return this;
	}

	public BenutzerDto setNachname(String nachname)
	{
		this.nachname = nachname;
		return this;
	}

	public BenutzerDto setGeburtsdatum(LocalDate geburtsdatum)
	{
		this.geburtsdatum = geburtsdatum;
		return this;
	}

	public BenutzerDto setKraftlevel(String kraftlevel)
	{
		this.kraftlevel = kraftlevel;
		return this;
	}

	public BenutzerDto setGeschlecht(String geschlecht)
	{
		this.geschlecht = geschlecht;
		return this;
	}

	public BenutzerDto setErfahrung(String erfahrung)
	{
		this.erfahrung = erfahrung;
		return this;
	}

	public BenutzerDto setErnaehrung(String ernaehrung)
	{
		this.ernaehrung = ernaehrung;
		return this;
	}

	public BenutzerDto setSchlafqualitaet(String schlafqualitaet)
	{
		this.schlafqualitaet = schlafqualitaet;
		return this;
	}

	public BenutzerDto setStress(String stress)
	{
		this.stress = stress;
		return this;
	}

	public BenutzerDto setDoping(String doping)
	{
		this.doping = doping;
		return this;
	}

	public BenutzerDto setRegenerationsfaehigkeit(String regenerationsfaehigkeit)
	{
		this.regenerationsfaehigkeit = regenerationsfaehigkeit;
		return this;
	}

	public BenutzerDto setAuthentifizierung(AuthentifizierungDto authentifizierung)
	{
		this.authentifizierung = authentifizierung;
		return this;
	}

	public double getKoerpergewicht()
	{
		return koerpermessungen.get(koerpermessungen.size() - 1).getKoerpergewicht();
	}

	public BenutzerDto fuegeKoerpermessungHinzu(KoerpermessungDto koerpermessung)
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
		var that = (BenutzerDto) o;
		return primaerschluessel.equals(that.primaerschluessel) &&
			vorname.equals(that.vorname) &&
			nachname.equals(that.nachname) &&
			geburtsdatum.equals(that.geburtsdatum) &&
			kraftlevel.equals(that.kraftlevel) &&
			geschlecht.equals(that.geschlecht) &&
			erfahrung.equals(that.erfahrung) &&
			ernaehrung.equals(that.ernaehrung) &&
			schlafqualitaet.equals(that.schlafqualitaet) &&
			stress.equals(that.stress) &&
			doping.equals(that.doping) &&
			regenerationsfaehigkeit.equals(that.regenerationsfaehigkeit) &&
			authentifizierung.equals(that.authentifizierung) &&
			koerpermessungen.equals(that.koerpermessungen);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(primaerschluessel, vorname, nachname, geburtsdatum, kraftlevel, geschlecht, erfahrung,
			ernaehrung, schlafqualitaet, stress, doping, regenerationsfaehigkeit, authentifizierung, koerpermessungen);
	}
}
