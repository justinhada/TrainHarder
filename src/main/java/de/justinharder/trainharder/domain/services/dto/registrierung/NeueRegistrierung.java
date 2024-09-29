package de.justinharder.trainharder.domain.services.dto.registrierung;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.NeuesDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NeueRegistrierung extends NeuesDTO<NeueRegistrierung>
{
	@JsonProperty(value = "eMailAdresse", required = true)
	private String eMailAdresse;

	@JsonProperty(value = "passwort", required = true)
	private String passwort;
}
