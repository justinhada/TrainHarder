package de.justinharder.trainharder.view;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;

public class CORSFilter implements ContainerResponseFilter
{
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
	{
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
		responseContext.getHeaders()
			.add("Access-Control-Allow-Headers", "accept, origin, authorization, content-type, x-requested-with");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
		responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
	}
}
