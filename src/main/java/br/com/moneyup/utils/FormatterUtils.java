package br.com.moneyup.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatterUtils
{

	private static final Locale			BRAZILIAN_LOCALE		= new Locale("pt", "BR");

	private static final NumberFormat	CURRENCY_FORMATTER	= NumberFormat.getCurrencyInstance(BRAZILIAN_LOCALE);

	private FormatterUtils()
	{
	}

	public static String toCurrency(String value)
	{
		return CURRENCY_FORMATTER.format(Double.valueOf(value));
	}

}
