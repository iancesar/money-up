package br.com.spdata.spdpayslip.unit;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Teste
{

	@Test
	void testName() throws Exception
	{
		System.out.println(new BCryptPasswordEncoder().encode("33619987"));
	}

}
