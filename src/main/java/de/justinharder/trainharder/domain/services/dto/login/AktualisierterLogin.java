package de.justinharder.trainharder.domain.services.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AktualisierterLogin extends AktualisiertesDTO<AktualisierterLogin>
{
	@JsonProperty(value = "eMailAdresse", required = true)
	private String eMailAdresse;

	@JsonProperty(value = "benutzername", required = true)
	private String benutzername;

	@JsonProperty(value = "passwort", required = true)
	private String passwort;
}
