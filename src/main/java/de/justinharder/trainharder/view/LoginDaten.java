package de.justinharder.trainharder.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginDaten
{
	@NonNull
	@JsonProperty(required = true)
	private String benutzername;

	@NonNull
	@JsonProperty(required = true)
	private String passwort;
}
