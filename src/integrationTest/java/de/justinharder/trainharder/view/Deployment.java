package de.justinharder.trainharder.view;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.*;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.File;
import java.nio.file.Paths;

@Slf4j
@Testcontainers
@NoArgsConstructor(access = AccessLevel.PROTECTED)
abstract class Deployment
{
	@ClassRule
	private static final Network NETZWERK = Network.newNetwork();

	@Container
	private static final MariaDBContainer<?> MARIA_DB_CONTAINER = new MariaDBContainer<>(
		DockerImageName.parse("mariadb"))
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
			.withFileFromPath("build/libs/TrainHarder.war", Paths.get("build/libs/TrainHarder.war"))
			.withFileFromPath("config/wildfly", Paths.get("config/wildfly"))
			.withFileFromPath("Dockerfile", Paths.get("Dockerfile")))
		.withLogConsumer(new Slf4jLogConsumer(log))
		.withNetwork(NETZWERK)
		.withNetworkAliases("webapp")
		.withExposedPorts(8080)
		.waitingFor(Wait.forHttp("/"))
		.withFileSystemBind("build/jacocoit", "/jacocoReport", BindMode.READ_WRITE)
		.withFileSystemBind("build/jacocoAgent", "/jacocoAgent", BindMode.READ_WRITE)
		.withEnv("JAVA_TOOL_OPTIONS", "-javaagent:/jacocoAgent/org.jacoco.agent.jar=destfile=/jacocoReport/jacoco-docker.exec")
		.dependsOn(MARIA_DB_CONTAINER);

	@Container
	private final BrowserWebDriverContainer<?> chromeContainer = new BrowserWebDriverContainer<>(
		DockerImageName.parse("selenium/standalone-chrome-debug"))
		.withLogConsumer(new Slf4jLogConsumer(log))
		.withNetwork(NETZWERK)
		.withSharedMemorySize(3221225472L)
		.withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING, new File("./build/vnc"))
		.withCapabilities(new ChromeOptions())
		.dependsOn(WILDFLY_CONTAINER);

	protected void navigiere(String endpunkt)
	{
		webseite().navigate().to(String.format("http://webapp:8080/TrainHarder/%s", endpunkt));
	}

	protected RemoteWebDriver webseite()
	{
		return chromeContainer.getWebDriver();
	}

	@AfterAll
	static void beendeWildflyJvmFreundlich()
	{
		WILDFLY_CONTAINER
			.getDockerClient()
			.stopContainerCmd(WILDFLY_CONTAINER.getContainerId())
			.withTimeout(60)
			.exec();
	}
}