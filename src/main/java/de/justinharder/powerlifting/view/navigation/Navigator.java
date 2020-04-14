package de.justinharder.powerlifting.view.navigation;

import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Navigator implements Serializable
{
	private static final long serialVersionUID = -5900551477725997768L;

	private static final String STARTSEITE_URL = "/Powerlifting";
	private static final String FEHLER_URL = "fehler.xhtml";
	private static final String LOGIN_URL = "login.xhtml";
	private static final String UEBUNGSAUSWAHL_URL = "uebungsauswahl.xhtml";
	private static final String KRAFTWERT_ERSTELLEN_URL = "erstellen.xhtml";
	private static final String ERFOLGREICH_URL = "success.xhtml";

	public String zurStartseite()
	{
		return STARTSEITE_URL;
	}

	public String zurFehlerseite(final Fehlermeldung fehlermeldung)
	{
		return parametrisiereUrl(FEHLER_URL, Parameter.fromFehlermeldung(fehlermeldung));
	}

	public String zurRegistrierungErfolgreichMailBestaetigung(final String authentifizierungId)
	{
		return parametrisiereUrl(ERFOLGREICH_URL, Parameter.fromAuthentifizierung(authentifizierungId));
	}

	public String zurLoginSeite(final Fehlermeldung fehlermeldung)
	{
		return parametrisiereUrl(LOGIN_URL, Parameter.fromFehlermeldung(fehlermeldung));
	}

	public String zurUebungsauswahlSeite(final String benutzerId)
	{
		return parametrisiereUrl(UEBUNGSAUSWAHL_URL, Parameter.fromBenutzer(benutzerId));
	}

	public String zurKraftwertErstellenSeite(final String benutzerId, final String uebungId)
	{
		return parametrisiereUrl(KRAFTWERT_ERSTELLEN_URL,
			Parameter.fromBenutzer(benutzerId),
			Parameter.fromUebung(uebungId));
	}

	private String parametrisiereUrl(final String url, final Parameter... parameter)
	{
		final var parameterBeginn = "?";
		final var mehrereParameterTrenner = "&";
		final var parameterWertpaare = Stream.of(parameter)
			.map(Parameter::toString)
			.collect(Collectors.joining(mehrereParameterTrenner));

		return url + parameterBeginn + String.join(parameterBeginn, parameterWertpaare);
	}
}
