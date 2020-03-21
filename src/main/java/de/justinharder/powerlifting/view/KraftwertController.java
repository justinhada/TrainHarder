package de.justinharder.powerlifting.view;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.dto.KraftwertEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.powerlifting.model.services.KraftwertService;
import de.justinharder.powerlifting.view.navigation.ExternerWebContext;
import de.justinharder.powerlifting.view.navigation.Navigator;
import lombok.Getter;

@Getter
@Named
@SessionScoped
public class KraftwertController extends Controller
{
	private static final String KRAFTWERT_ID = "kraftwertId";

	private final KraftwertService kraftwertService;
	private final KraftwertEintrag kraftwertEintrag = new KraftwertEintrag();

	@Inject
	public KraftwertController(
		final ExternerWebContext externerWebContext,
		final Navigator navigator,
		final KraftwertService kraftwertService)
	{
		super(externerWebContext, navigator);
		this.kraftwertService = kraftwertService;
	}

	public List<KraftwertEintrag> getKraftwerte()
	{
		return kraftwertService.ermittleAlle();
	}

	public List<KraftwertEintrag> getKraftwerteZuBenutzer(final Benutzer benutzer)
	{
		return kraftwertService.ermittleAlleZuBenutzer(benutzer);
	}

	public KraftwertEintrag getKraftwertZuId() throws KraftwertNichtGefundenException
	{
		return kraftwertService.ermittleZuId(getRequestParameter(KRAFTWERT_ID));
	}

	public void erstelleKraftwert(final String uebungId, final String benutzerId)
	{
		kraftwertService.erstelleKraftwert(
			kraftwertEintrag,
			uebungId,
			benutzerId);
	}

	@Override
	public String getRequestParameter(final String parameterBezeichnung)
	{
		return super.getRequestParameter(parameterBezeichnung);
	}
}
