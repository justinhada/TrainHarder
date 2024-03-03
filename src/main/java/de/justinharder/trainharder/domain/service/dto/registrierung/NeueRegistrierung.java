package de.justinharder.trainharder.domain.service.dto.registrierung;

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
public class NeueRegistrierung extends NeuesDTO<NeueRegistrierung>
{
	@JsonProperty(value = "eMailAdresse", required = true)
	private String eMailAdresse;

	@JsonProperty(value = "passwort", required = true)
	private String passwort;
}
