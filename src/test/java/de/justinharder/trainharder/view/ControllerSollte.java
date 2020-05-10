package de.justinharder.trainharder.view;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.BeforeEach;

import de.justinharder.trainharder.view.navigation.ExternerWebContext;
import de.justinharder.trainharder.view.navigation.Navigator;

public class ControllerSollte
{
	protected ExternerWebContext externerWebContext;
	protected Navigator navigator = mock(Navigator.class);

	@BeforeEach
	protected void setupController()
	{
		externerWebContext = mock(ExternerWebContext.class);
	}

	@SafeVarargs
	protected final void angenommenExternerWebContextEnthaeltParameter(final Entry<String, String>... parameter)
	{
		final Map<String, String> requestParameter = new HashMap<>();
		List.of(parameter)
			.forEach(p -> requestParameter.put(p.getKey(), p.getValue()));
		when(externerWebContext.getRequestParameter()).thenReturn(requestParameter);
	}
}
