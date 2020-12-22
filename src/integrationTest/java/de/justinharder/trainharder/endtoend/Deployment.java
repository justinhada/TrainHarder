package de.justinharder.trainharder.endtoend;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.ClassRule;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;

@Testcontainers
@NoArgsConstructor(access = AccessLevel.PROTECTED)
abstract class Deployment
{
	@ClassRule
	private static final Network NETZWERK = Network.newNetwork();

	private static final String PERSISTENCE_UNIT_NAME = "TestRepoPU";
	private static final Logger LOGGER = LoggerFactory.getLogger(Deployment.class);

	@Container
	private static final MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>(DockerImageName.parse("mariadb"))
		.withLogConsumer(new Slf4jLogConsumer(LOGGER))
		.withNetwork(NETZWERK)
		.withNetworkAliases("mariadbhost")
		.withExposedPorts(3306)
		.withDatabaseName("trainharderTest")
		.withUsername("powerlifter")
		.withPassword("passwort");

	@Container
	private static final GenericContainer<?> wildflyContainer = new GenericContainer<>(
		new ImageFromDockerfile()
			.withFileFromPath("build/libs/TrainHarder-0.0.2.war", Paths.get("build/libs/TrainHarder-0.0.2.war"))
			.withFileFromPath("config/wildfly", Paths.get("config/wildfly"))
			.withFileFromPath("Dockerfile", Paths.get("Dockerfile")))
		.withLogConsumer(new Slf4jLogConsumer(LOGGER))
		.withNetwork(NETZWERK)
		.withNetworkAliases("webapp")
		.waitingFor(Wait.forHttp("/"))
		.dependsOn(mariaDBContainer);

	@Container
	private final BrowserWebDriverContainer<?> chromeContainer = new BrowserWebDriverContainer<>(
		DockerImageName.parse("selenium/standalone-chrome-debug"))
		.withLogConsumer(new Slf4jLogConsumer(LOGGER))
		.withNetwork(NETZWERK)
		.withSharedMemorySize(21474836L)
		.withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING, new File("./build"))
		.withCapabilities(new ChromeOptions())
		.dependsOn(wildflyContainer);

	protected void navigiere(String endpunkt)
	{
		var url = String.format("http://%s:%s/%s/%s",
			"localhost",
			"8080",
			"TrainHarder",
			endpunkt);
		webseite().navigate().to(url);
	}

	protected RemoteWebDriver webseite()
	{
		return chromeContainer.getWebDriver();
	}

	private static EntityManager entityManager;

	protected static EntityManager getEntityManager()
	{
		if (entityManager != null)
		{
			return entityManager;
		}

		var props = new HashMap<String, Object>();
		props.put("javax.persistence.jdbc.url", mariaDBContainer.getJdbcUrl());
		props.put("javax.persistence.jdbc.driver", mariaDBContainer.getDriverClassName());
		props.put("javax.persistence.jdbc.user", mariaDBContainer.getUsername());
		props.put("javax.persistence.jdbc.password", mariaDBContainer.getPassword());
		props.put("javax.persistence.schema-generation.database.action", "drop-and-create");
		props.put("hibernate.show_sql", "true");

		entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, props).createEntityManager();
		return entityManager;
	}

	protected static void schliesseEntityManager()
	{
		if (entityManager.getTransaction().isActive())
		{
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManager = null;
	}
}
