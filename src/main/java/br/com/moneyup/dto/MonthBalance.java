package br.com.moneyup.dto;

import java.math.BigDecimal;

import br.com.moneyup.util.Formatter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MonthBalance
{
	private BigDecimal balance;

	public String getBalanceFormatted()
	{
		return Formatter.toCurrency(balance);
	}
}
