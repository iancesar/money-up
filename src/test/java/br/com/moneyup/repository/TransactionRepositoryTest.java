package br.com.moneyup.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.moneyup.H2DBTests;
import br.com.moneyup.entity.Transaction;
import br.com.moneyup.entity.User;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryTest extends H2DBTests
{

	@Autowired
	private TransactionRepository	transactionRepository;

	@Mock
	private User						user;

	@Test
	void list()
	{
		try
		{

			when(user.getId()).thenReturn(1L);

			List<Transaction> transactions = transactionRepository.find(user, (short) 2020, (short) 7);

			assertThat(transactions).hasSize(2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected String getDataFile()
	{
		return "transaction";
	}

}
