package de.justinharder.powerlifting.view;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.dto.BenutzerEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.BenutzerNichtGefundenException;
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

	private int id;
	private String vorname;
	private String nachname = "";
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

	private List<BenutzerEintrag> alleBenutzer = new ArrayList<>();

	@Inject
	public BenutzerController(final BenutzerService benutzerService)
	{
		this.benutzerService = benutzerService;
	}

	public List<BenutzerEintrag> getBenutzer()
	{
		return benutzerService.ermittleAlle();
	}

	public BenutzerEintrag getBenutzerZuId() throws BenutzerNichtGefundenException
	{
		return benutzerService.ermittleZuId(id);
	}

	public BenutzerEintrag erstelleBenutzer()
	{
		return benutzerService.erstelleBenutzer(vorname, nachname, koerpergewicht, koerpergroesse, lebensalter,
			geschlecht, erfahrung, ernaehrung, schlafqualitaet, stress, doping, regenerationsfaehigkeit);
	}

	public void getBenutzerZuNachname() throws BenutzerNichtGefundenException
	{
		alleBenutzer = benutzerService.ermittleZuNachname(nachname);
	}
}
