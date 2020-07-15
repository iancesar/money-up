package br.com.moneyup.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.moneyup.entity.enumerate.TransactionType;
import br.com.moneyup.util.Formatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionsDTO implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	private Long					id;

	private String					title;

	private BigDecimal			price;

	private TransactionType		type;

	public String getPriceFormatted()
	{
		return Formatter.toCurrency(price);
	}

}
