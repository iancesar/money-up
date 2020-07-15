package br.com.moneyup;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password
{

	@Test
	void testName() throws Exception
	{
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
	
}
