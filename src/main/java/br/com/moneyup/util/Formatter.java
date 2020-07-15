package br.com.moneyup.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Formatter
{

	private static final Locale			BRAZILIAN_LOCALE		= new Locale("pt", "BR");

	private static final NumberFormat	CURRENCY_FORMATTER	= NumberFormat.getCurrencyInstance(BRAZILIAN_LOCALE);

	private Formatter()
	{
	}

	public static String toCurrency(String value)
	{
		return CURRENCY_FORMATTER.format(Double.valueOf(value));
	}

	public static String toCurrency(BigDecimal value)
	{
		return CURRENCY_FORMATTER.format(value.doubleValue());
	}

}
