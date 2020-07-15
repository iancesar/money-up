package br.com.moneyup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moneyup.dto.JwtRequest;
import br.com.moneyup.dto.JwtResponse;
import br.com.moneyup.entity.User;
import br.com.moneyup.repository.UserRepository;
import br.com.moneyup.security.JwtTokenUtil;

@RestController
@RequestMapping(path = "/api/login")
public class LoginController
{

	@Autowired
	private AuthenticationManager	authenticationManager;

	@Autowired
	private JwtTokenUtil				jwtTokenUtil;

	@Autowired
	private UserRepository			userRepository;

	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception
	{
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		User user = userRepository.findByEmail(authenticationRequest.getEmail()).get();

		final String token = jwtTokenUtil.generateToken(user);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception
	{
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(DisabledException e)
		{
			throw new Exception("USER_DISABLED", e);
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
