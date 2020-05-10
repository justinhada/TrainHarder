package de.justinharder.trainharder.view;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.trainharder.model.domain.dto.KraftwertEintrag;
import de.justinharder.trainharder.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.trainharder.model.services.KraftwertService;
import de.justinharder.trainharder.view.navigation.ExternerWebContext;
import de.justinharder.trainharder.view.navigation.Navigator;
import lombok.Getter;

@Getter
@Named
@SessionScoped
public class KraftwertController extends Controller
{
	private static final long serialVersionUID = -2503601527239164789L;

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

	public List<KraftwertEintrag> getKraftwerteZuBenutzer(final String benutzerId)
	{
		return kraftwertService.ermittleAlleZuBenutzer(benutzerId);
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