package br.com.moneyup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moneyup.entity.CreditCard;
import br.com.moneyup.entity.CreditCardPurchase;

public interface CreditCardPurchaseRepository extends JpaRepository<CreditCardPurchase, Long>
{

	List<CreditCardPurchase> findByCreditCard(CreditCard creditCard);

}
