package de.justinharder.trainharder.domain.services.dto.registrierung;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AktualisierteRegistrierung extends AktualisiertesDTO<AktualisierteRegistrierung>
{
	@JsonProperty(value = "eMailAdresse", required = true)
	private String eMailAdresse;

	@JsonProperty(value = "benutzername", required = true)
	private String benutzername;

	@JsonProperty(value = "vorname", required = true)
	private String vorname;

	@JsonProperty(value = "nachname", required = true)
	private String nachname;

	@JsonProperty(value = "geschlecht", required = true)
	private String geschlecht;

	@JsonProperty(value = "geburtsdatum", required = true)
	private String geburtsdatum;
}
