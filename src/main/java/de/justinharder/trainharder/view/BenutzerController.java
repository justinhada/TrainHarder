package de.justinharder.trainharder.view;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.trainharder.model.domain.dto.AuthentifizierungEintrag;
import de.justinharder.trainharder.model.domain.dto.BenutzerEintrag;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.view.navigation.ExternerWebContext;
import de.justinharder.trainharder.view.navigation.Navigator;
import lombok.Getter;

@Getter
@Named
@SessionScoped
public class BenutzerController extends Controller
{
	private static final long serialVersionUID = -6981486797529025609L;

	private static final String BENUTZER_ID = "benutzerId";
	private final BenutzerService benutzerService;
	private final BenutzerEintrag benutzerEintrag = new BenutzerEintrag();
	private final AuthentifizierungEintrag authentifizierungEintrag = new AuthentifizierungEintrag();
	private List<BenutzerEintrag> benutzerEintraege = new ArrayList<>();

	@Inject
	public BenutzerController(
		final ExternerWebContext externerWebContext,
		final Navigator navigator,
		final BenutzerService benutzerService)
	{
		super(externerWebContext, navigator);
		this.benutzerService = benutzerService;
	}

	public List<BenutzerEintrag> getBenutzer()
	{
		return benutzerService.ermittleAlle();
	}

	public BenutzerEintrag getBenutzerZuId() throws BenutzerNichtGefundenException
	{
		return benutzerService.ermittleZuId(getRequestParameter(BENUTZER_ID));
	}

	public void erstelleBenutzer()
	{
		benutzerService.erstelleBenutzer(benutzerEintrag, authentifizierungEintrag);
	}

	public void getBenutzerZuNachname(final String nachname) throws BenutzerNichtGefundenException
	{
		benutzerEintraege = benutzerService.ermittleZuNachname(nachname);
	}
}