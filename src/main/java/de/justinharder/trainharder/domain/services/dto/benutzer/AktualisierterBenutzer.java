package de.justinharder.trainharder.domain.services.dto.benutzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AktualisierterBenutzer extends AktualisiertesDTO<AktualisierterBenutzer>
{
	@JsonProperty(value = "vorname", required = true)
	private String vorname;

	@JsonProperty(value = "nachname", required = true)
	private String nachname;

	@JsonProperty(value = "geschlecht", required = true)
	private String geschlecht;

	@JsonProperty(value = "geburtsdatum", required = true)
	private String geburtsdatum;
}
