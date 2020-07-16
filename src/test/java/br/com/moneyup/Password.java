package br.com.moneyup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password
{

	@Test
	void testName() throws Exception
	{
		String a = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmssSSS"));
		System.out.println(a);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
