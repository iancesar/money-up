package br.com.moneyup.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.moneyup.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable
{
	private static final long	serialVersionUID		= -2550185165626007488L;

	public static final long	JWT_TOKEN_VALIDITY	= 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String					secret;

	public <T> T getClaimFromToken(String token, String claimName, Class<T> requiredType)
	{
		return getClaimFromToken(token, c -> c.get(claimName, requiredType));
	}

	public Long getUserIdFromToken(String token)
	{
		return getClaimFromToken(token, c -> c.get("id", Long.class));
	}

	//retrieve username from jwt token
	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	}

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	//for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token)
	{
		if(token.startsWith("Bearer "))
		{
			token = token.substring(7);
		}

		return Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).getBody();
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token)
	{
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(User user)
	{
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", user.getId());
		claims.put("name", user.getName());

		return doGenerateToken(claims, user.getEmail());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject)
	{
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, getSecret()).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails)
	{
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String getSecret()
	{
		return secret;
	}
}