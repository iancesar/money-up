package br.com.moneyup.dto.creditcard;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardRequest implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	@NotNull
	@NotEmpty
	private String					name;

	@NotNull
	private BigDecimal			limit;

	@NotNull
	private Short				due;
}
