package de.justinharder.powerlifting.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.dto.BenutzerEintrag;
import de.justinharder.powerlifting.model.services.BenutzerService;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
@Getter
@Setter
public class BenutzerController
{
	private final BenutzerService benutzerService;

	private String vorname;
	private String nachname;
	private int koerpergewicht;
	private int koerpergroesse;
	private int lebensalter;
	private String geschlecht;
	private String erfahrung;
	private String ernaehrung;
	private String schlafqualitaet;
	private String stress;
	private String doping;
	private String regenerationsfaehigkeit;

	@Inject
	public BenutzerController(final BenutzerService benutzerService)
	{
		this.benutzerService = benutzerService;
	}

	public List<BenutzerEintrag> getBenutzer()
	{
		return benutzerService.ermittleAlle();
	}

	public BenutzerEintrag erstelleBenutzer()
	{
		return benutzerService.erstelleBenutzer(vorname, nachname, koerpergewicht, koerpergroesse, lebensalter,
			geschlecht, erfahrung, ernaehrung, schlafqualitaet, stress, doping, regenerationsfaehigkeit);
	}
}
