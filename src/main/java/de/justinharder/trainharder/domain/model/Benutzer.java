package de.justinharder.trainharder.domain.model;

import com.google.common.base.MoreObjects;
import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.attribute.Geburtsdatum;
import de.justinharder.trainharder.domain.model.attribute.Geschlecht;
import de.justinharder.trainharder.domain.model.attribute.Nachname;
import de.justinharder.trainharder.domain.model.attribute.Vorname;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Benutzer extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 213867611479242533L;

	@NonNull
	@Embedded
	private Nachname nachname;

	@NonNull
	@Embedded
	private Vorname vorname;

	@NonNull
	@Column(name = "Geschlecht")
	@Enumerated(EnumType.STRING)
	private Geschlecht geschlecht;

	@NonNull
	@Embedded
	private Geburtsdatum geburtsdatum;

	public Benutzer(
		ID id,
		@NonNull Nachname nachname,
		@NonNull Vorname vorname,
		@NonNull Geschlecht geschlecht,
		@NonNull Geburtsdatum geburtsdatum)
	{
		super(id);
		this.nachname = nachname;
		this.vorname = vorname;
		this.geschlecht = geschlecht;
		this.geburtsdatum = geburtsdatum;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
			.add("ID", id)
			.add("Nachname", nachname)
			.add("Vorname", vorname)
			.add("Geschlecht", geschlecht)
			.add("Geburtsdatum", geburtsdatum)
			.toString();
	}
}
