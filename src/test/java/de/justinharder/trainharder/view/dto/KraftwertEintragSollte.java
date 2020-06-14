package de.justinharder.trainharder.view.dto;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.code.beanmatchers.BeanMatchers;

import de.justinharder.trainharder.view.dto.KraftwertEintrag;

public class KraftwertEintragSollte
{
	@Test
	@DisplayName("ein Bean sein")
	public void test01()
	{
		BeanMatchers.registerValueGenerator(() -> LocalDate.of(2019, 1, new Random().nextInt(28) + 1),
			LocalDate.class);
		assertThat(KraftwertEintrag.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanEquals(),
			hasValidBeanHashCode(),
			hasValidBeanToString()));
	}
}
