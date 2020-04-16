package de.justinharder.powerlifting;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@Path("/")
@ApplicationPath("/Powerlifting")
public class PowerliftingApplication extends Application
{
	@GET
	public String index()
	{
		return "/index.xhtml";
	}
}
