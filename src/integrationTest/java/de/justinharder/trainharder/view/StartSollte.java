package de.justinharder.trainharder.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StartSollte extends Deployment
{
	@Test
	@DisplayName("Text anzeigen")
	void test01()
	{
		navigiere("start");

		var elements = webseite().findElementsByTagName("div");

		assertThat(elements).isEmpty();
	}
}
