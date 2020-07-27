package de.justinharder.trainharder.model.services.mail;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.view.dto.Login;

public class MailAdresseSollte
{
	@Test
	@DisplayName("ein Bean sein")
	public void test01()
	{
		assertThat(Login.class, allOf(
			hasValidGettersAndSetters(),
			hasValidBeanEquals(),
			hasValidBeanHashCode(),
			hasValidBeanToString()));
	}
}
