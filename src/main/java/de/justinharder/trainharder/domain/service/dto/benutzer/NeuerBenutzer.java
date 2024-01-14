package de.justinharder.trainharder.domain.service.dto.benutzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
