package de.justinharder.powerlifting.view.navigation;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class ExternerWebContext
{
	private final ExternalContext externalContext;

	@Inject
	public ExternerWebContext()
	{
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
	}

	public Map<String, String> getRequestParameter()
	{
		return externalContext.getRequestParameterMap();
	}

	public void navigiere(final String url) throws IOException
	{
		externalContext.redirect(url);
	}
}
