package br.com.moneyup.security;

import static org.mockito.Mockito.when;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.moneyup.entity.User;
import br.com.moneyup.service.UserService;

@ExtendWith(value = MockitoExtension.class)
public class JwtRequestFilterTest
{

	JwtRequestFilter		filter	= new JwtRequestFilter();

	@Mock
	HttpServletRequest	request;

	@Mock
	HttpServletResponse	response;

	@Mock
	FilterChain				filterChain;

	@Mock
	UserService				userService;

	@Mock
	CustomUserDetails				userDetails;

	@Test
	void getNullHeader() throws Exception
	{

		when(request.getHeader(Mockito.anyString())).thenReturn(null);

		filter.doFilterInternal(request, response, filterChain);
	}

	@Test
	void getWrongRequestTokenPrefix() throws Exception
	{
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil("123");

		when(request.getHeader(Mockito.anyString())).thenReturn("Wrong ");
		
		filter.jwtTokenUtil = jwtTokenUtil;

		filter.doFilterInternal(request, response, filterChain);
	}

	@Test
	void getIllegalToken() throws Exception
	{
		when(request.getHeader(Mockito.anyString())).thenReturn("Bearer wrongtoken");

		filter.doFilterInternal(request, response, filterChain);
	}

	@Test
	void getLegalToken() throws Exception
	{
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil("123");

		when(userDetails.getUsername()).thenReturn("teste@teste.com");
		when(userService.loadUserByUsername(Mockito.anyString())).thenReturn(userDetails);

		filter.jwtTokenUtil = jwtTokenUtil;
		filter.userService = userService;

		when(request.getHeader(Mockito.anyString()))
			.thenReturn(String.join(" ", "Bearer", jwtTokenUtil.generateToken(new User(1L, "teste", "teste@teste.com", "teste"))));

		filter.doFilterInternal(request, response, filterChain);
	}
}
