package de.justinharder.trainharder;

import de.justinharder.trainharder.setup.TestdatenAnleger;
import lombok.SneakyThrows;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
