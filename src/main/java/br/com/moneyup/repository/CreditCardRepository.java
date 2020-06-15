package br.com.moneyup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moneyup.entity.CreditCard;
import br.com.moneyup.entity.User;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long>
{

	List<CreditCard> findByUser(User one);

	Optional<CreditCard> findByIdAndUser(Long id, User user);

}
