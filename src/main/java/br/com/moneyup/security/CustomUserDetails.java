package br.com.moneyup.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User
{

	private static final long				serialVersionUID	= 1L;

	private br.com.moneyup.entity.User	user;

	public CustomUserDetails(br.com.moneyup.entity.User user)
	{
		super(user.getEmail(), user.getPassword(), true, true, true, true, new ArrayList<>());
		this.user = user;
	}

	public br.com.moneyup.entity.User getUser()
	{
		return user;
	}

}
