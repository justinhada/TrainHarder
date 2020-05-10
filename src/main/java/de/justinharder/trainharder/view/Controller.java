package de.justinharder.trainharder.view;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;

import de.justinharder.trainharder.view.navigation.ExternerWebContext;
import de.justinharder.trainharder.view.navigation.Fehlermeldung;
import de.justinharder.trainharder.view.navigation.Navigator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Controller implements Serializable
{
	private static final long serialVersionUID = -6049359953046927355L;

	protected final ExternerWebContext externerWebContext;
	protected final Navigator navigator;

	@Inject
	public Controller(final ExternerWebContext externerWebContext, final Navigator navigator)
	{
		this.externerWebContext = externerWebContext;
		this.navigator = navigator;
	}

	protected String getRequestParameter(final String parameterBezeichnung)
	{
		try
		{
			return externerWebContext.getRequestParameter().get(parameterBezeichnung);
		}
		catch (final Exception e)
		{
			log.error("Fehler beim Ermitteln des Request-Parameters!", e);
			return "";
		}
	}

	protected String ermittlePflichtparameterwert(final String parametername)
	{
		final var parameterwert = externerWebContext.getRequestParameter().get(parametername);
		if (parameterwert == null || parameterwert.isEmpty())
		{
			log.error("Der Pflichtparameterwert für den Parameternamen \"" + parametername
				+ "\" konnte nicht aus der URL ermittelt werden.");
			navigiere(navigator.zurFehlerseite(Fehlermeldung.PARAMETER_NICHT_VORHANDEN));
		}
		return parameterwert;
	}

	private void navigiere(final String url)
	{
		try
		{
			externerWebContext.navigiere(url);
		}
		catch (final IOException e)
		{
			throw new RuntimeException(
				"Es ist ein kritischer Fehler aufgetreten. Die direkte Navigation zur URL \"" + url
					+ "\" ist fehlgeschlagen.",
				e);
		}
	}
}
