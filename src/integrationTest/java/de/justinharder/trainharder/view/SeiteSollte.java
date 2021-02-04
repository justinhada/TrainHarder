package de.justinharder.trainharder.view;

import static org.assertj.core.api.Assertions.assertThat;

public class SeiteSollte extends Deployment
{
	protected static final String ALT = "alt";
	protected static final String ANCHOR = "a";
	protected static final String DIV = "div";
	protected static final String FORM = "form";
	protected static final String H2 = "h2";
	protected static final String IMG = "img";
	protected static final String INPUT = "input";
	protected static final String OPTION = "option";
	protected static final String PLACEHOLDER = "placeholder";
	protected static final String SELECT = "select";
	protected static final String SPAN = "span";
	protected static final String SUBMIT = "submit";
	protected static final String TEXT = "text";
	protected static final String TYPE = "type";
	protected static final String VALUE = "value";

	protected String seite;

	protected void setup(String seite)
	{
		this.seite = seite;
	}

	protected void testeTitel(String erwartet)
	{
		navigiere(seite);

		assertThat(webseite().getTitle()).isEqualTo(erwartet);
	}
}
