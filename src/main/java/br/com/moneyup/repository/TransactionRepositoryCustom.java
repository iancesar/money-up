package br.com.moneyup.repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import br.com.moneyup.entity.Transaction;
import br.com.moneyup.entity.User;

public interface TransactionRepositoryCustom
{

	BigDecimal getBalance(Short year, Short month, Long long1) throws IOException;

	List<Transaction> find(User user, Short year, Short month);

}
