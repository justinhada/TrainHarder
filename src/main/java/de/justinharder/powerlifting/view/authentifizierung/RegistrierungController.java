package de.justinharder.powerlifting.view.authentifizierung;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.dto.Registrierung;
import de.justinharder.powerlifting.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.powerlifting.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.powerlifting.model.domain.exceptions.MailBereitsRegistriertException;
import de.justinharder.powerlifting.model.domain.exceptions.PasswortNichtSicherException;
import de.justinharder.powerlifting.model.services.AuthentifizierungService;
import de.justinharder.powerlifting.view.Controller;
import de.justinharder.powerlifting.view.navigation.ExternerWebContext;
import de.justinharder.powerlifting.view.navigation.Navigator;
import lombok.Getter;
import lombok.Setter;

@Named
@Getter
@Setter
@ApplicationScoped
public class RegistrierungController extends Controller
{
	private static final long serialVersionUID = 2133789122383485814L;

	private final AuthentifizierungService authentifizierungService;
	private final Map<String, String> fehlermeldungen = new HashMap<>();
	private final Registrierung registrierung = new Registrierung();

	@Inject
	public RegistrierungController(
		final ExternerWebContext externerWebContext,
		final Navigator navigator,
		final AuthentifizierungService authentifizierungService)
	{
		super(externerWebContext, navigator);
		this.authentifizierungService = authentifizierungService;
	}

	public String registriere()
	{
		try
		{
			final var authentifizierung = authentifizierungService.registriere(registrierung);
			return navigator.zurRegistrierungErfolgreichMailBestaetigung(String.valueOf(authentifizierung.getId()))
				.concat("&faces-redirect=true");
		}
		catch (final MailBereitsRegistriertException | BenutzernameVergebenException | PasswortNichtSicherException
			| AuthentifizierungNichtGefundenException e)
		{
			fehlermeldungen.put(e.getClass().getSimpleName(), e.getMessage());
			return "join.xhtml?faces-redirect=true";
		}
	}

	public String erfolgreich()
	{
		try
		{
			System.out.println(getRequestParameter("AuthentifizierungID"));
			return authentifizierungService.ermittleZuId(getRequestParameter("AuthentifizierungID"))
				.getBenutzername();
		}
		catch (final AuthentifizierungNichtGefundenException e)
		{
			return "";
		}
	}
}
