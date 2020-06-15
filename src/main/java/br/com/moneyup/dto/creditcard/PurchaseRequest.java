package br.com.moneyup.dto.creditcard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseRequest implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	@NotNull
	@NotEmpty
	private String					name;

	@NotNull
	private BigDecimal			price;

	@NotNull
	private LocalDate				date;

	private Short					installments;
}
