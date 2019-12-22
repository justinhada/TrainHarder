package de.justinharder.powerlifting.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.dto.BenutzerEintrag;
import de.justinharder.powerlifting.model.services.BenutzerService;

@Named
@RequestScoped
public class BenutzerController
{
	private final BenutzerService benutzerService;

	@Inject
	public BenutzerController(final BenutzerService benutzerService)
	{
		this.benutzerService = benutzerService;
	}

	public List<BenutzerEintrag> getBenutzer()
	{
		return benutzerService.ermittleAlle();
	}
}
