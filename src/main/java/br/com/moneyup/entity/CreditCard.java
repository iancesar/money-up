package br.com.moneyup.entity;

import java.math.BigDecimal;

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
public class CreditCard
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long			id;

	@Column
	@NotNull
	@NotEmpty
	private String			name;

	/**
	 * Limit is a reserved word in Mysql
	 */
	@Column(name = "limit_credit", precision = 19, scale = 2)
	@NotNull
	private BigDecimal	limit;

	@Column
	@NotNull
	private Short			due;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private User			user;

}
