package de.justinharder.powerlifting.view.login;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.justinharder.powerlifting.model.domain.exceptions.LoginException;
import de.justinharder.powerlifting.model.services.AnmeldedatenService;
import de.justinharder.powerlifting.view.navigation.Fehlermeldung;
import de.justinharder.powerlifting.view.navigation.Navigator;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = -7478174212877375776L;

	private final AnmeldedatenService anmeldedatenService;

	@Inject
	public LoginServlet(final AnmeldedatenService anmeldedatenService)
	{
		super();
		this.anmeldedatenService = anmeldedatenService;
	}

	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
		throws ServletException, IOException
	{
		final var mail = request.getParameter("mail");
		final var passwort = request.getParameter("passwort");

		var ziel = "login.xhtml";
		try
		{
			final var anmeldedatenEintrag = anmeldedatenService.checkLogin(mail, passwort);
			final var session = request.getSession();
			session.setAttribute("benutzer", anmeldedatenEintrag.getId());
			ziel = new Navigator().zurStartseite();
		}
		catch (final LoginException e)
		{
			final var fehlermeldung = Fehlermeldung.LOGIN_FEHLGESCHLAGEN.getNachricht();
			request.setAttribute("fehlermeldung", fehlermeldung);
		}

		final var dispatcher = request.getRequestDispatcher(ziel);
		dispatcher.forward(request, response);
	}

	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
		throws ServletException, IOException
	{
		super.doGet(request, response);
	}
}
