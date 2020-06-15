package br.com.moneyup;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.moneyup.factory.ModelMapperFactory;

@SpringBootApplication
public class MoneyUpApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(MoneyUpApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapperFactory().getModelMapper();
	}

}
