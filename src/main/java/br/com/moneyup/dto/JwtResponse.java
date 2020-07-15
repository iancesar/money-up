package br.com.moneyup.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class JwtResponse implements Serializable
{
	private static final long	serialVersionUID	= -8091879091924046844L;

	private final String			token;

	public JwtResponse(String jwttoken)
	{
		this.token = jwttoken;
	}
}
