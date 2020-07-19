package br.com.moneyup.repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.io.IOUtils;

import br.com.moneyup.entity.Transaction;
import br.com.moneyup.entity.User;

public class TransactionRepositoryImpl implements TransactionRepositoryCustom
{
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public BigDecimal getBalance(Short year, Short month, Long userId) throws IOException
	{
		String sql = IOUtils.toString(getClass().getResource("/sql/balance.sql"), StandardCharsets.UTF_8);

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("user_id", userId);

		BigDecimal balance = (BigDecimal) query.getSingleResult();

		return balance != null ? balance : BigDecimal.ZERO;
	}

	@Override
	public List<Transaction> find(User user, Short year, Short month)
	{
		String sql = "select t from Transaction t where month(t.date) = :month and year(t.date) = :year and t.user = :user";
		TypedQuery<Transaction> query = entityManager.createQuery(sql, Transaction.class);
		query.setParameter("month", month.intValue());
		query.setParameter("year", year.intValue());
		query.setParameter("user", user);

		return query.getResultList();
	}

}
