package de.justinharder.base.domain.model.attribute;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Datum extends WertObjekt<LocalDate>
{
	@Serial
	private static final long serialVersionUID = 5066170286071040307L;

	@NonNull
	@Column(name = "Datum", nullable = false)
	private LocalDate wert;

	public Datum(@NonNull String wert)
	{
		this(LocalDate.parse(wert, DateTimeFormatter.ofPattern("dd.MM.yyyy")));
	}

	@Override
	public String toString()
	{
		return wert.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}
}
