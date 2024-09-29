package de.justinharder.trainharder.domain.services.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.justinharder.base.domain.services.dto.AktualisiertesDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AktualisierterLogin extends AktualisiertesDTO<AktualisierterLogin>
{
	@JsonProperty(value = "eMailAdresse", required = true)
	private String eMailAdresse;

	@JsonProperty(value = "benutzername", required = true)
	private String benutzername;

	@JsonProperty(value = "passwort", required = true)
	private String passwort;
}
