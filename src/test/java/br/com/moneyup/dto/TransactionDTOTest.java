package br.com.moneyup.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import br.com.moneyup.entity.Transaction;
import br.com.moneyup.entity.User;
import br.com.moneyup.entity.enumerate.TransactionType;

public class TransactionDTOTest
{
	@Test
	void testName() throws Exception
	{
		User user = new User(1l, "Teste", "teste", "teste");

		Transaction t = new Transaction(1l, "Teste", BigDecimal.ONE, user, TransactionType.EXPENSE, false, LocalDate.now());

		TransactionsDTO dto = new ModelMapper().map(t, TransactionsDTO.class);

		assertThat(dto).hasNoNullFieldsOrProperties();

	}

}
