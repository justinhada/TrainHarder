package de.justinharder.trainharder.domain.model.attribute;

import de.justinharder.trainharder.domain.model.enums.*;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@Accessors(chain = true)
public class Benutzerangabe implements Serializable
{
	@Serial
	private static final long serialVersionUID = 9094368590669673429L;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Kraftlevel")
	private Kraftlevel kraftlevel = Kraftlevel.CLASS_5;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Geschlecht")
	private Geschlecht geschlecht;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Erfahrung")
	private Erfahrung erfahrung;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Ernaehrung")
	private Ernaehrung ernaehrung;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Schlafqualitaet")
	private Schlafqualitaet schlafqualitaet;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Stress")
	private Stress stress;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Doping")
	private Doping doping;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Regenerationsfaehigkeit")
	private Regenerationsfaehigkeit regenerationsfaehigkeit;

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Benutzerangabe that))
		{
			return false;
		}
		return kraftlevel == that.kraftlevel
			&& geschlecht == that.geschlecht
			&& erfahrung == that.erfahrung
			&& ernaehrung == that.ernaehrung
			&& schlafqualitaet == that.schlafqualitaet
			&& stress == that.stress
			&& doping == that.doping
			&& regenerationsfaehigkeit == that.regenerationsfaehigkeit;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(kraftlevel, geschlecht, erfahrung, ernaehrung, schlafqualitaet, stress, doping,
			regenerationsfaehigkeit);
	}
}
