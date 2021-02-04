package de.justinharder.trainharder;

import de.justinharder.trainharder.model.domain.enums.Enums;
import de.justinharder.trainharder.setup.TestdatenAnleger;
import lombok.SneakyThrows;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebListener
public class StartListener implements ServletContextListener
{
	enum Stage
	{
		DEVELOPMENT,
		PRODUCTION;

		static Stage zuWert(String name)
		{
			return Enums.zuWert(Stream.of(values()).collect(Collectors.toMap(Function.identity(), Enum::name)), name);
		}
	}

	@Inject
	private TestdatenAnleger testdatenAnleger;

	@Override
	@SneakyThrows
	public void contextInitialized(ServletContextEvent servletContextEvent)
	{
		if (Stage.zuWert((String) new InitialContext().lookup("java:global/stage")) == Stage.DEVELOPMENT)
		{
			testdatenAnleger.legeTestdatenAn();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent)
	{}
}
