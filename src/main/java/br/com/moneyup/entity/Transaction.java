/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moneyup.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.moneyup.entity.enumerate.TransactionType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ian
 */
@Entity
@Getter
@Setter
@Table(name = "\"transaction\"")
public class Transaction implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long					id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "title")
	private String					title;

	@Basic(optional = false)
	@NotNull
	@Column(name = "price", precision = 19, scale = 2)
	private BigDecimal			price;

	@ManyToOne
	@NotNull
	private User					user;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	private TransactionType		type;

	@NotNull
	@Column
	private Boolean				consolidated;

	@NotNull
	@Column
	private LocalDate				date;

	public Transaction()
	{
	}

	public Transaction(Long id, @NotNull @Size(min = 1, max = 100) String title, @NotNull BigDecimal price, @NotNull User user,
		@NotNull TransactionType type, @NotNull Boolean consolidated, LocalDate date)
	{
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.user = user;
		this.type = type;
		this.consolidated = consolidated;
		this.date = date;
	}

}
