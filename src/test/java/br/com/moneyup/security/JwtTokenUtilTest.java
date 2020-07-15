package br.com.moneyup.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.moneyup.entity.User;
import io.jsonwebtoken.ExpiredJwtException;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(value = MockitoExtension.class)
public class JwtTokenUtilTest
{

	private JwtTokenUtil		tokenUtil	= new JwtTokenUtil("123");

	private static String	token;

	@Mock
	private UserDetails		userDetails;

	@Test
	@Order(1)
	void generateToken() throws Exception
	{
		User user = new User(1L, "teste", "teste@teste.com.br", "teste");
		token = tokenUtil.generateToken(user);
		assertThat(token).isNotNull();
	}

	@Test
	void validateToken() throws Exception
	{
		when(userDetails.getUsername()).thenReturn("teste@teste.com.br");

		assertThat(tokenUtil.validateToken(token, userDetails)).isTrue();
	}

	@Test
	void validateTokenWithUserDetailWrong() throws Exception
	{
		when(userDetails.getUsername()).thenReturn("wrong@teste.com.br");

		assertThat(tokenUtil.validateToken(token, userDetails)).isFalse();
	}

	@Test
	void validateTokenWithTokenExpired() throws Exception
	{
		User user = new User(1L, "teste", "teste@teste.com.br", "teste");
		String token = tokenUtil.generateToken(user, Date.from(LocalDateTime.now().minusDays(10).atZone(ZoneId.systemDefault()).toInstant()));

		Assertions.assertThrows(ExpiredJwtException.class, () -> {
			tokenUtil.validateToken(token, userDetails);
		});
	}

	@Test
	void getClaimFromToken() throws Exception
	{
		assertThat(tokenUtil.getClaimFromToken(token, "id", Long.class)).isNotNull();
		assertThat(tokenUtil.getClaimFromToken(token, "id", Long.class)).isEqualTo(1L);
	}

	@Test
	void getUserIdFromToken() throws Exception
	{
		assertThat(tokenUtil.getUserIdFromToken(token)).isEqualTo(1L);
	}

	@Test
	void getClaimWithTokenStartsWithBaerer() throws Exception
	{
		String tokenAux = String.join(" ", "Bearer", token);
		assertThat(tokenUtil.getUserIdFromToken(tokenAux)).isEqualTo(1L);

	}
}
