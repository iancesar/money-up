package br.com.moneyup.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FormatterTest
{

	@Test
	void toCurrency() throws Exception
	{
		assertThat(Formatter.toCurrency("100")).isEqualTo("R$ 100,00");
		assertThat(Formatter.toCurrency("100.25")).isEqualTo("R$ 100,25");
	}

}
