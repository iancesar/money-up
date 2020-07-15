package br.com.moneyup.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class TransactionDTO implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	@NotNull
	private String					title;

	@NotNull
	private BigDecimal			price;

	protected TransactionDTO()
	{
	}

}
