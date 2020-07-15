package br.com.moneyup.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.moneyup.service.UserService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter
{

	@Autowired
	protected UserService	userService;

	@Autowired
	protected JwtTokenUtil	jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException
	{
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
		{
			jwtToken = StringUtils.substring(requestTokenHeader, 7);
			username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		}
		else
		{
			logger.warn("JWT Token does not begin with Bearer String");
		}
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			CustomUserDetails userDetails = userService.loadUserByUsername(username);
		
			if(jwtTokenUtil.validateToken(jwtToken, userDetails))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		filterChain.doFilter(request, response);

	}
}
