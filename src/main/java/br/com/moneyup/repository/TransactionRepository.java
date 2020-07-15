package br.com.moneyup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moneyup.entity.Transaction;
import br.com.moneyup.entity.User;

public interface TransactionRepository extends JpaRepository<Transaction, Long>
{

	List<Transaction> findByUser(User user);

	Optional<Transaction> findByUserAndId(User user, Long id);

}
