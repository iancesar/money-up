package br.com.moneyup.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.moneyup.H2DBTests;

@SpringBootTest
public class UserServiceTest extends H2DBTests
{

	@Autowired
	private UserService userService;

	@Test
	void loadUserByUsername() throws Exception
	{
		assertThat(userService.loadUserByUsername("teste@teste.com")).isNotNull();
	}

	@Test
	void loadUserByWrongUsername() throws Exception
	{
		Assertions.assertThrows(UsernameNotFoundException.class, () -> {
			userService.loadUserByUsername("wrong@teste.com");
		});
	}

	@Override
	protected String getDataFile()
	{
		return "user";
	}

}
