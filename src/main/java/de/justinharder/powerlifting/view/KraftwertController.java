package de.justinharder.powerlifting.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.dto.KraftwertEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.KraftwertNichtGefundenException;
import de.justinharder.powerlifting.model.services.KraftwertService;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
@Getter
@Setter
public class KraftwertController
{
	private KraftwertService kraftwertService;

	private int id;
	private Uebung uebung;
	private int maximum;
	private Benutzer benutzer;

	@Inject
	public KraftwertController(final KraftwertService kraftwertService)
	{
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
		return kraftwertService.ermittleZuId(id);
	}

	public KraftwertEintrag erstelleKraftwert()
	{
		return kraftwertService.erstelleKraftwert(uebung, maximum, benutzer);
	}
}
