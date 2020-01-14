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
	private static final String UEBUNGSAUSWAHL = "uebungsauswahl.xhtml";

	public String zurFehlerseite(final Fehlermeldung fehlermeldung)
	{
		return parametrisiereUrl(FEHLER_URL, Parameter.fromFehlermeldung(fehlermeldung));
	}

	public String zurUebungsauswahlSeite(final String berufId)
	{
		return parametrisiereUrl(UEBUNGSAUSWAHL, Parameter.fromBenutzer(berufId));
	}

	private String parametrisiereUrl(final String url, final Parameter... parameter)
	{
		final var parameterBeginn = "?";
		final var parameterWertpaare = Stream.of(parameter)
			.map(Parameter::toString)
			.collect(Collectors.toList());

		return url + parameterBeginn + String.join(parameterBeginn, parameterWertpaare);
	}
}
