package br.com.moneyup.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.moneyup.entity.enumerate.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	@NotNull
	private String					title;

	@NotNull
	private BigDecimal			price;

	@NotNull
	private TransactionType		type;

	private Boolean				consolidated		= false;

	private LocalDate				date					= LocalDate.now();

	protected TransactionDTO()
	{
	}

}
