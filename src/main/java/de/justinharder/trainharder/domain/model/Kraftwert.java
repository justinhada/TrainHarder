package de.justinharder.trainharder.domain.model;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.enums.Wiederholungen;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Kraftwert extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -1203157961547955006L;

	@Setter
	@NonNull
	@Column(name = "Datum")
	private LocalDate datum;

	@Setter
	@NonNull
	@Column(name = "Gewicht", precision = 7, scale = 2)
	private BigDecimal gewicht;

	@Setter
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Wiederholungen")
	private Wiederholungen wiederholungen;

	@Setter
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UebungID", nullable = false)
	private Uebung uebung;

	@Setter
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public Kraftwert(
		ID id,
		@NonNull LocalDate datum,
		@NonNull BigDecimal gewicht,
		@NonNull Wiederholungen wiederholungen,
		@NonNull Uebung uebung,
		@NonNull Benutzer benutzer)
	{
		super(id);
		this.datum = datum;
		this.gewicht = gewicht;
		this.wiederholungen = wiederholungen;
		this.uebung = uebung;
		this.benutzer = benutzer;
	}
}
