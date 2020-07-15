package br.com.moneyup.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.moneyup.dto.TransactionDTO;
import br.com.moneyup.security.CustomUserDetails;

@RestController
@RequestMapping(path = "/api/transaction")
public class TransactionController
{

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@AuthenticationPrincipal CustomUserDetails customUser, @RequestBody @Valid TransactionDTO transactionDTO)
	{
		//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//		authentication.getPrincipal()
		System.out.println(transactionDTO.getPrice());
	}

}
