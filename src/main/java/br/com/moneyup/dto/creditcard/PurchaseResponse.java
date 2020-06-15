package br.com.moneyup.dto.creditcard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseResponse implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	private Long					id;

	private String					name;

	private BigDecimal			price;

	private LocalDate				date;

}
