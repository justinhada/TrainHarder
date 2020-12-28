package de.justinharder.trainharder.view.navigation;

import de.justinharder.trainharder.model.services.KontaktService;
import de.justinharder.trainharder.view.AbstractController;
import de.justinharder.trainharder.view.dto.Kontaktformular;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.Setter;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.binding.BindingResult;
import javax.mvc.binding.ParamError;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.stream.Collectors;

@Controller
@Path(value = "/kontakt")
public class KontaktController extends AbstractController
{
	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletRequest request;
	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletResponse response;
	@Inject
	private BindingResult bindingResult;

	@Inject
	private KontaktService kontaktService;

	public void setBindingResult(@NonNull BindingResult bindingResult)
	{
		this.bindingResult = bindingResult;
	}

	public void setKontaktService(@NonNull KontaktService kontaktService)
	{
		this.kontaktService = kontaktService;
	}

	@GET
	@Override
	public String index()
	{
		return initialisiere("/kontakt.xhtml");
	}

	@POST
	public String kontaktiere(@NonNull @BeanParam Kontaktformular kontaktformular)
	{
		if (bindingResult.isFailed())
		{
			models.put("fehler", bindingResult.getAllErrors().stream()
				.map(ParamError::getMessage)
				.collect(Collectors.toList()));
			return index();
		}

		kontaktService.kontaktiere(kontaktformular);
		models.put("kontaktformular", kontaktformular);
		return erfolgreich();
	}

	@GET
	@Path(value = "/success")
	public String erfolgreich()
	{
		return "/kontaktiert.xhtml";
	}
}
