package de.justinharder.powerlifting.view.navigation;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Navigator
{
	private static final String FEHLER_URL = "fehler.xhtml";
	private static final String UEBUNGSAUSWAHL_URL = "uebungsauswahl.xhtml";
	private static final String KRAFTWERT_ERSTELLEN_URL = "erstellen.xhtml";

	public String zurStartseite()
	{
		return "/Powerlifting";
	}

	public String zurFehlerseite(final Fehlermeldung fehlermeldung)
	{
		return parametrisiereUrl(FEHLER_URL, Parameter.fromFehlermeldung(fehlermeldung));
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
