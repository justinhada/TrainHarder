package de.justinharder.trainharder.view;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
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

@Slf4j
@Testcontainers
@NoArgsConstructor(access = AccessLevel.PROTECTED)
abstract class Deployment
{
	@ClassRule
	private static final Network NETZWERK = Network.newNetwork();

	private static final String PERSISTENCE_UNIT_NAME = "TestRepoPU";

	@Container
	private static final MariaDBContainer<?> MARIA_DB_CONTAINER = new MariaDBContainer<>(DockerImageName.parse("mariadb"))
		.withLogConsumer(new Slf4jLogConsumer(log))
		.withNetwork(NETZWERK)
		.withNetworkAliases("mariadbhost")
		.withExposedPorts(3306)
		.withDatabaseName("trainharderTest")
		.withUsername("powerlifter")
		.withPassword("passwort");

	@Container
	private static final GenericContainer<?> WILDFLY_CONTAINER = new GenericContainer<>(
		new ImageFromDockerfile()
			.withFileFromPath("build/libs/TrainHarder-0.0.2.war", Paths.get("build/libs/TrainHarder-0.0.2.war"))
			.withFileFromPath("config/wildfly", Paths.get("config/wildfly"))
			.withFileFromPath("Dockerfile", Paths.get("Dockerfile")))
		.withLogConsumer(new Slf4jLogConsumer(log))
		.withNetwork(NETZWERK)
		.withNetworkAliases("webapp")
		.waitingFor(Wait.forHttp("/"))
		.dependsOn(MARIA_DB_CONTAINER);

	@Container
	private final BrowserWebDriverContainer<?> chromeContainer = new BrowserWebDriverContainer<>(
		DockerImageName.parse("selenium/standalone-chrome-debug"))
		.withLogConsumer(new Slf4jLogConsumer(log))
		.withNetwork(NETZWERK)
		.withSharedMemorySize(21474836L)
		.withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING, new File("./build"))
		.withCapabilities(new ChromeOptions())
		.dependsOn(WILDFLY_CONTAINER);

	protected void navigiere(String endpunkt)
	{
		webseite().navigate().to(String.format("http://%s:%s/%s/%s", "webapp", "8080", "TrainHarder", endpunkt));
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
		props.put("javax.persistence.jdbc.url", MARIA_DB_CONTAINER.getJdbcUrl());
		props.put("javax.persistence.jdbc.driver", MARIA_DB_CONTAINER.getDriverClassName());
		props.put("javax.persistence.jdbc.user", MARIA_DB_CONTAINER.getUsername());
		props.put("javax.persistence.jdbc.password", MARIA_DB_CONTAINER.getPassword());
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
