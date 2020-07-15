package br.com.moneyup.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
@Entity
@Table(name = "\"user\"")
public class User implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long					id;

	@Column
	@NotNull
	@NotEmpty
	private String					name;

	@Column(unique = true)
	@NotNull
	@NotEmpty
	@Email
	private String					email;

	@Column
	@NotNull
	@NotEmpty
	private String					password;

	protected User()
	{
	}

	public User(Long id, @NotNull @NotEmpty String name, @NotNull @NotEmpty @Email String email, @NotNull @NotEmpty String password)
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

}
