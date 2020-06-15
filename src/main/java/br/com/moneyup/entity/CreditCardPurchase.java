package br.com.moneyup.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCardPurchase
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long					id;

	@Column
	@NotNull
	@NotEmpty
	private String					name;

	@Column(precision = 19, scale = 2)
	@NotNull
	private BigDecimal			price;

	@Column
	@NotNull
	private LocalDate date;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private CreditCard			creditCard;

	@ManyToOne(fetch = FetchType.LAZY)
	private CreditCardPurchase	originatedFrom;

}
