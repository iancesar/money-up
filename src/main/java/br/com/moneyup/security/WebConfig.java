package br.com.moneyup.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer
{
	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		String[] headers = new String[8];
		headers[0] = "X-Auth-Token";
		headers[1] = "Authorization";
		headers[2] = "Origin";
		headers[3] = "Accept";
		headers[4] = "X-Requested-With";
		headers[5] = "Content-Type";
		headers[6] = "Access-Control-Request-Method";
		headers[7] = "Access-Control-Request-Headers";

		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE, OPTIONS, Authorization").allowedHeaders(headers);
	}

//	@Override
//	public void addResourceHandlers(final ResourceHandlerRegistry registry)
//	{
//		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//	}
}
