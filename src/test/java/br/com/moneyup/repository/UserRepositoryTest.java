package br.com.moneyup.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest
{

	@Autowired
	private UserRepository userRepository;

	@Test
	void findByEmail() throws Exception
	{
		assertThat(userRepository.findByEmail("teste@teste.com")).isPresent();
	}

}
