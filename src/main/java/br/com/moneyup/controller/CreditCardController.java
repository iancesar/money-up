package br.com.moneyup.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moneyup.dto.creditcard.CreditCardRequest;
import br.com.moneyup.dto.creditcard.CreditCardResponse;
import br.com.moneyup.dto.creditcard.PurchaseRequest;
import br.com.moneyup.dto.creditcard.PurchaseResponse;
import br.com.moneyup.service.CreditCardService;

@RestController
@RequestMapping(path = "/credit-card")
public class CreditCardController
{

	@Autowired
	private CreditCardService creditCardService;

	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity create(@RequestHeader(name = "Authorization") String token, @RequestBody @Valid CreditCardRequest creditCard)
	{
		creditCardService.create(token, creditCard);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<List<CreditCardResponse>> list(@RequestHeader(name = "Authorization") String token)
	{
		return ResponseEntity.ok(creditCardService.list(token));
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/{creditCard}/purchase")
	public ResponseEntity newPurchase(@RequestHeader(name = "Authorization") String token, @PathVariable("creditCard") Long card,
		@RequestBody @Valid PurchaseRequest purchaseRequest)
	{
		creditCardService.newPurchase(token, card, purchaseRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@GetMapping(path = "/{creditCard}/purchase")
	public ResponseEntity<List<PurchaseResponse>> newPurchase(@RequestHeader(name = "Authorization") String token,
		@PathVariable("creditCard") Long card)
	{
		return ResponseEntity.ok(creditCardService.getPurchase(token, card));

	}

}
