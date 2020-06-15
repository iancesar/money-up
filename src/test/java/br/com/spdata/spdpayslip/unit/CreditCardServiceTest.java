package br.com.spdata.spdpayslip.unit;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.moneyup.dto.creditcard.PurchaseRequest;
import br.com.moneyup.entity.CreditCard;
import br.com.moneyup.entity.CreditCardPurchase;
import br.com.moneyup.service.CreditCardService;

public class CreditCardServiceTest
{

	@Test
	void testName() throws Exception
	{

		CreditCard c = new CreditCard();
		c.setDue((short)8);

		PurchaseRequest p = new PurchaseRequest();
		p.setInstallments((short) 7);
		p.setPrice(BigDecimal.valueOf(147.18));
		p.setDate(LocalDate.now());
		p.setName("Picpay");

		List<CreditCardPurchase> list = new CreditCardService().applyInstallments(c, p).get();

		list.forEach(l -> {
			System.out.println(l.getName());
			System.out.println(l.getPrice());
			System.out.println(l.getDate());
		});

		/*
		MathContext mc = new MathContext(4);

		Double total = 100d;
		Long installments = 3L;

		BigDecimal val = BigDecimal.valueOf(total);

		val = val.divide(BigDecimal.valueOf(installments), mc);

		System.out.println(val);

		val = val.multiply(BigDecimal.valueOf(installments));

		BigDecimal rest = BigDecimal.valueOf(total).subtract(val);

		System.out.println(val.add(rest));*/

	}

}
