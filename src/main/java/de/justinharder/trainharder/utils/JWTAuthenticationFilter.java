package de.justinharder.trainharder.utils;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptor;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.Priority;
import java.security.interfaces.RSAPublicKey;

@Provider
@Interceptor
@RequestScoped
@JWTAuthentication
@Priority(Priorities.AUTHENTICATION)
public class JWTAuthenticationFilter implements ContainerRequestFilter
{
	@Inject
	JsonWebToken jwt;

	@Inject
	RSAPublicKey rsaPublicKey;

	@Override
	public void filter(ContainerRequestContext containerRequestContext)
	{
		// Extract JWT from the HTTP Authorization header
		var token = extractToken(containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION));

		// Validate and process the JWT token
		//if (token != null && jwt.verify(token))
		//{
		// Token is valid, proceed with the request
		//}
		//else
		//{
		// Token is invalid or missing, abort the request
		//    throw new WebApplicationException("Invalid JWT token", Response.Status.UNAUTHORIZED);
		//}
	}

	private String extractToken(String authorizationHeader)
	{
		// Extract and return the JWT token from the Authorization header
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
		{
			// Extract and return the token after removing the "Bearer " prefix
			return authorizationHeader.substring("Bearer ".length()).trim();
		}

		// Return null if the Authorization header is missing or doesn't start with "Bearer"
		return null;
	}
}
