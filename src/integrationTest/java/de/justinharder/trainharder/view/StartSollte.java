package de.justinharder.trainharder.endtoend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StartSollte extends Deployment
{
	@Test
	@DisplayName("Text anzeigen")
	void test01()
	{
		navigiere("start");

		var text = webseite().findElementByClassName("center-container big-container mt-5");

		assertThat(text.getText()).isEqualTo("");
	}
}
