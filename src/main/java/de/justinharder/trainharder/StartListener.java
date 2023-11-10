package de.justinharder.trainharder;

import de.justinharder.trainharder.setup.TestdatenAnleger;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;

@WebListener
public class StartListener implements ServletContextListener
{
	@Inject
	private TestdatenAnleger testdatenAnleger;

	@Override
	@SneakyThrows
	public void contextInitialized(ServletContextEvent servletContextEvent)
	{
		testdatenAnleger.legeTestdatenAn();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent)
	{}
}
