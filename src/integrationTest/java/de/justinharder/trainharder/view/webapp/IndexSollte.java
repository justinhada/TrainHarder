package de.justinharder.trainharder.view.webapp;

import de.justinharder.trainharder.view.SeiteSollte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IndexSollte extends SeiteSollte
{
	@BeforeEach
	void setup()
	{
		setup("start");
	}

	@Test
	@DisplayName("Grundstruktur einer Seite mit Navigation einhalten")
	void test01()
	{
		testeTitel("TrainHarder");
	}
}
