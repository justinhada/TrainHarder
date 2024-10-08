package de.justinharder.trainharder.domain.services.dto.login;

import de.justinharder.base.domain.services.dto.NeuesDTO;
import de.justinharder.trainharder.domain.model.Registrierung;
import de.justinharder.trainharder.domain.model.attribute.EMailAdresse;
import de.justinharder.trainharder.domain.model.attribute.Passwort;
import de.justinharder.trainharder.domain.model.attribute.Salt;
import de.justinharder.trainharder.domain.services.dto.benutzer.NeuerBenutzer;
import de.justinharder.trainharder.domain.services.dto.registrierung.AktualisierteRegistrierung;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NeuerLogin extends NeuesDTO<NeuerLogin>
{
	private EMailAdresse eMailAdresse;

	private String benutzername;

	private Salt salt;

	private Passwort passwort;

	private String benutzerId;

	public static NeuerLogin aus(
		@NonNull Registrierung registrierung,
		@NonNull AktualisierteRegistrierung aktualisierteRegistrierung,
		@NonNull NeuerBenutzer neuerBenutzer)
	{
		return new NeuerLogin(
			registrierung.getEMailAdresse(),
			aktualisierteRegistrierung.getBenutzername(),
			registrierung.getSalt(),
			registrierung.getPasswort(),
			neuerBenutzer.getId());
	}
}
