package de.justinharder.trainharder.domain.model.attribute;

import de.justinharder.base.domain.model.attribute.WertObjekt;
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
public class Geburtsdatum extends WertObjekt<LocalDate>
{
	@Serial
	private static final long serialVersionUID = -2601851527447173145L;

	@NonNull
	@Column(name = "Geburtsdatum", nullable = false)
	private LocalDate wert;

	public Geburtsdatum(@NonNull String wert)
	{
		this(LocalDate.parse(wert, DateTimeFormatter.ISO_DATE));
	}

	@Override
	public String toString()
	{
		return wert.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}
}
