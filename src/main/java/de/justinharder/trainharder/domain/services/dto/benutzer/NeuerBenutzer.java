package de.justinharder.trainharder.domain.services.dto.benutzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import de.justinharder.trainharder.domain.services.dto.registrierung.AktualisierteRegistrierung;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NeuerBenutzer extends NeuesDTO<NeuerBenutzer>
{
	@JsonProperty(value = "vorname", required = true)
	private String vorname;

	@JsonProperty(value = "nachname", required = true)
	private String nachname;

	@JsonProperty(value = "geschlecht", required = true)
	private String geschlecht;

	@JsonProperty(value = "geburtsdatum", required = true)
	private String geburtsdatum;

	public static NeuerBenutzer aus(@NonNull AktualisierteRegistrierung aktualisierteRegistrierung)
	{
		return new NeuerBenutzer(
			aktualisierteRegistrierung.getVorname(),
			aktualisierteRegistrierung.getNachname(),
			aktualisierteRegistrierung.getGeschlecht(),
			aktualisierteRegistrierung.getGeburtsdatum());
	}
}
