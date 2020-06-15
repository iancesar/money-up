package br.com.moneyup.service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moneyup.dto.creditcard.CreditCardRequest;
import br.com.moneyup.dto.creditcard.CreditCardResponse;
import br.com.moneyup.dto.creditcard.PurchaseRequest;
import br.com.moneyup.dto.creditcard.PurchaseResponse;
import br.com.moneyup.entity.CreditCard;
import br.com.moneyup.entity.CreditCardPurchase;
import br.com.moneyup.exception.BusinessException;
import br.com.moneyup.repository.CreditCardPurchaseRepository;
import br.com.moneyup.repository.CreditCardRepository;
import br.com.moneyup.repository.UserRepository;
import br.com.moneyup.security.JwtTokenUtil;

@Service
public class CreditCardService
{

	@Autowired
	private JwtTokenUtil							tokenUtil;

	@Autowired
	private UserRepository						userRepository;

	@Autowired
	private CreditCardRepository				creditCardRepository;

	@Autowired
	private CreditCardPurchaseRepository	creditCardPurchaseRepository;

	@Autowired
	private ModelMapper							modelMapper;

	public void create(String token, CreditCardRequest creditCardRequest)
	{
		Long userId = tokenUtil.getUserIdFromToken(token);
		CreditCard creditCard = modelMapper.map(creditCardRequest, CreditCard.class);
		creditCard.setUser(userRepository.getOne(userId));

		creditCardRepository.save(creditCard);
	}

	public List<CreditCardResponse> list(String token)
	{
		Long userId = tokenUtil.getUserIdFromToken(token);

		List<CreditCard> cards = creditCardRepository.findByUser(userRepository.getOne(userId));

		Type listType = new TypeToken<List<CreditCardResponse>>()
		{
		}.getType();

		return modelMapper.map(cards, listType);
	}

	public void newPurchase(String token, Long card, PurchaseRequest purchaseRequest)
	{
		Long userId = tokenUtil.getUserIdFromToken(token);

		CreditCard creditCard = creditCardRepository.findByIdAndUser(card, userRepository.getOne(userId))
			.orElseThrow(() -> new BusinessException("Esse cartão de crédito não pertence ao usuário"));

		if(purchaseRequest.getInstallments() != null && purchaseRequest.getInstallments() > 1)
		{
			List<CreditCardPurchase> creditCardPurchases = applyInstallments(creditCard, purchaseRequest).get();
			final CreditCardPurchase mainCreditCardPurchase = creditCardPurchaseRepository.saveAndFlush(creditCardPurchases.get(0));

			creditCardPurchases.remove(0);
			creditCardPurchases.forEach(i -> i.setOriginatedFrom(mainCreditCardPurchase));

			creditCardPurchaseRepository.saveAll(creditCardPurchases);
		}
		else
		{
			CreditCardPurchase creditCardPurchase = modelMapper.map(purchaseRequest, CreditCardPurchase.class);
			creditCardPurchase.setCreditCard(creditCard);
			creditCardPurchaseRepository.save(creditCardPurchase);
		}

	}

	public Supplier<List<CreditCardPurchase>> applyInstallments(CreditCard creditCard, PurchaseRequest purchaseRequest)
	{
		Supplier<List<CreditCardPurchase>> s = () -> {

			MathContext mc = new MathContext(4);

			BigDecimal times = BigDecimal.valueOf(purchaseRequest.getInstallments());

			BigDecimal installment = purchaseRequest.getPrice().divide(times, mc);

			BigDecimal rest = (purchaseRequest.getPrice().subtract(installment.multiply(times)));

			List<CreditCardPurchase> installments = new ArrayList<>();

			for(int i = 1; i <= purchaseRequest.getInstallments(); i++)
			{
				CreditCardPurchase purchase = new CreditCardPurchase();
				purchase.setCreditCard(creditCard);
				purchase.setName(String.format("%s (%dx)", purchaseRequest.getName(), i));

				if(purchaseRequest.getDate().getDayOfMonth() <= creditCard.getDue())
				{
					purchase.setDate(purchaseRequest.getDate().plusMonths(i - 1));
				}
				else
				{
					purchase.setDate(purchaseRequest.getDate().plusMonths(i));
				}

				if(i == purchaseRequest.getInstallments() && rest.compareTo(BigDecimal.ZERO) != 0)
				{
					purchase.setPrice(installment.add(rest)); //can be positive or negative value
				}
				else
				{
					purchase.setPrice(installment);
				}

				installments.add(purchase);
			}
			return installments;
		};

		return s;
	}

	public List<PurchaseResponse> getPurchase(String token, Long card)
	{
		Long userId = tokenUtil.getUserIdFromToken(token);

		CreditCard creditCard = creditCardRepository.findByIdAndUser(card, userRepository.getOne(userId))
			.orElseThrow(() -> new BusinessException("Esse cartão de crédito não pertence ao usuário"));

		List<CreditCardPurchase> purchases = creditCardPurchaseRepository.findByCreditCard(creditCard);

		Type listType = new TypeToken<List<PurchaseResponse>>()
		{
		}.getType();

		List<PurchaseResponse> purchaseResponse = modelMapper.map(purchases, listType);

		return purchaseResponse;
	}

}
