package de.justinharder.trainharder.model.services.mail;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MailAdresseSollte
{
	@Test
	@DisplayName("ein Bean sein")
	void test01()
	{
		assertThat(MailAdresse.class, allOf(
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanToString()));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test02()
	{
		EqualsVerifier.forClass(MailAdresse.class)
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new MailAdresse(null)),
			() -> assertThrows(NullPointerException.class, () -> new MailAdresse("mail@mail.de", null)));
	}
}