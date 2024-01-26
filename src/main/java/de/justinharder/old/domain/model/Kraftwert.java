package de.justinharder.old.domain.model;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.model.enums.Wiederholungen;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;

//@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Kraftwert extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -1203157961547955006L;

	@NonNull
	@Column(name = "Datum")
	private LocalDate datum;

	@NonNull
	@Column(name = "Gewicht", precision = 7, scale = 2)
	private BigDecimal gewicht;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Wiederholungen")
	private Wiederholungen wiederholungen;

	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UebungID", nullable = false)
	private Uebung uebung;

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
