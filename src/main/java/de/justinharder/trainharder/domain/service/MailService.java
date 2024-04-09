package de.justinharder.trainharder.domain.service;

import de.justinharder.trainharder.domain.model.Login;
import de.justinharder.trainharder.domain.model.Registrierung;
import io.quarkus.mailer.reactive.ReactiveMailer;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
@RequiredArgsConstructor
public class MailService
{
	@NonNull
	private final ReactiveMailer reactiveMailer;

	public void sendeNachErsterRegistrierung(@NonNull Registrierung registrierung)
	{
		var text = """
			Hallo!
						
			Mit dieser E-Mail senden wir dir deinen Bestätigungslink.
			Du kannst deine Registrierung über diesen Link abschließen.
			Viel Spaß!
						
			http://localhost:5173/registrierung/%s
						
			Mit sportlichen Grüßen
			dein TrainHarder-Team
			""".formatted(registrierung.getId().getWert());

		log.info(text);
	}

	public void sendeNachVollstaendigerRegistrierung(@NonNull Login login)
	{
		var text = """
			Hallo %s %s!
						
			Mit dieser E-Mail bestätigen wir, dass du deine Registrierung abgeschlossen hast.
			Du kannst dich jetzt über den folgenden Link mit deinem Benutzernamen und Passwort einloggen.
			Viel Spaß mit TrainHarder!
						
			http://localhost:5173/login
						
			Mit sportlichen Grüßen
			dein TrainHarder-Team
			""".formatted(login.getBenutzer().getVorname(), login.getBenutzer().getNachname());

		log.info(text);
	}
}
