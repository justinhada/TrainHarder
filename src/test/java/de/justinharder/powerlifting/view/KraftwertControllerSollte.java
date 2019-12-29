package de.justinharder.powerlifting.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KraftwertControllerSollte
{
	private KraftwertController sut;

	@BeforeEach
	public void setup()
	{
		sut = new KraftwertController();
	}

	@Test
	@DisplayName("eine Liste aller KraftwertBeiträge zurückgeben")
	public void test01()
	{
		final var erwartet = 2;
	}
}
