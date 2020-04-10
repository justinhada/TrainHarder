package de.justinharder.powerlifting.view;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.dto.AnmeldedatenEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.AnmeldedatenNichtGefundenException;
import de.justinharder.powerlifting.model.services.AnmeldedatenService;
import de.justinharder.powerlifting.view.navigation.ExternerWebContext;
import de.justinharder.powerlifting.view.navigation.Navigator;
import lombok.Getter;

@Getter
@Named
@SessionScoped
public class AnmeldedatenController extends Controller
{
	private static final long serialVersionUID = -3304162878990370933L;

	private final AnmeldedatenService anmeldedatenService;
	private final AnmeldedatenEintrag anmeldedatenEintrag = new AnmeldedatenEintrag();

	@Inject
	public AnmeldedatenController(
		final ExternerWebContext externerWebContext,
		final Navigator navigator,
		final AnmeldedatenService anmeldedatenService)
	{
		super(externerWebContext, navigator);
		this.anmeldedatenService = anmeldedatenService;
	}

	public List<AnmeldedatenEintrag> getAnmeldedaten()
	{
		return anmeldedatenService.ermittleAlle();
	}

	public AnmeldedatenEintrag getAnmeldedatenZuId() throws AnmeldedatenNichtGefundenException
	{
		return anmeldedatenService.ermittleZuId(getRequestParameter("anmeldedatenId"));
	}

	public AnmeldedatenEintrag getAnmeldedatenZuBenutzer() throws AnmeldedatenNichtGefundenException
	{
		return anmeldedatenService.ermittleZuBenutzer(getRequestParameter("benutzerId"));
	}

	public void erstelleAnmeldedaten()
	{
		anmeldedatenService.erstelleAnmeldedaten(anmeldedatenEintrag);
	}
}
