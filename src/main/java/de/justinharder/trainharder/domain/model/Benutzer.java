package de.justinharder.trainharder.domain.model;

import de.justinharder.trainharder.domain.model.embeddables.Benutzerangabe;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.embeddables.Name;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Benutzer extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 2411974948424821755L;

	@Setter
	@NonNull
	@Embedded
	private Name name;

	@Setter
	@NonNull
	@Column(name = "Geburtsdatum")
	private LocalDate geburtsdatum;

	@Setter
	@NonNull
	@Embedded
	private Benutzerangabe benutzerangabe;

	@Setter
	@NonNull
	@OneToOne(optional = false)
	@JoinColumn(name = "AuthentifizierungID", nullable = false)
	private Authentifizierung authentifizierung;

	@OneToMany
	@JoinColumn(name = "BenutzerID")
	private final List<Koerpermessung> koerpermessungen = new ArrayList<>();

	public Benutzer(
		ID id,
		@NonNull Name name,
		@NonNull LocalDate geburtsdatum,
		@NonNull Benutzerangabe benutzerangabe,
		@NonNull Authentifizierung authentifizierung)
	{
		super(id);
		this.name = name;
		this.geburtsdatum = geburtsdatum;
		this.benutzerangabe = benutzerangabe;
		this.authentifizierung = authentifizierung;
	}

	public int getAlter(@NonNull LocalDate datum)
	{
		return Period.between(geburtsdatum, datum).getYears();
	}

	public BigDecimal getKoerpergewicht()
	{
		return koerpermessungen.stream()
			.reduce((first, second) -> second)
			.map(koerpermessung -> koerpermessung.getKoerpermasse().getKoerpergewicht())
			.orElseGet(() -> null);
	}

	public BigDecimal getKoerpergroesse()
	{
		return koerpermessungen.stream()
			.reduce((first, second) -> second)
			.map(koerpermessung -> koerpermessung.getKoerpermasse().getKoerpergroesse())
			.orElseGet(() -> null);
	}

	public Benutzer fuegeKoerpermessungHinzu(@NonNull Koerpermessung koerpermessung)
	{
		koerpermessungen.add(koerpermessung);
		return this;
	}
}
