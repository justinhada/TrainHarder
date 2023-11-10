package de.justinharder.trainharder.view.navigation;

import de.justinharder.trainharder.model.services.KontaktService;
import de.justinharder.trainharder.view.AbstractController;
import de.justinharder.trainharder.view.dto.Kontaktformular;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import lombok.NonNull;

import java.util.stream.Collectors;

@Controller
@Path(value = "/kontakt")
public class KontaktController extends AbstractController
{
	@Context
	private HttpServletRequest request;
	@Context
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
