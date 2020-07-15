package br.com.moneyup.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.moneyup.dto.TransactionDTO;
import br.com.moneyup.dto.TransactionsDTO;
import br.com.moneyup.security.CustomUserDetails;
import br.com.moneyup.service.TransactionService;

@RestController
@RequestMapping(path = "/api/transaction")
public class TransactionController
{

	@Autowired
	private TransactionService transactionService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid TransactionDTO transactionDTO)
	{
		transactionService.create(transactionDTO, userDetails);
	}

	@GetMapping
	public List<TransactionsDTO> list(@AuthenticationPrincipal CustomUserDetails userDetails)
	{
		return transactionService.list(userDetails);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id)
	{
		transactionService.delete(userDetails, id);
	}

	@PatchMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody TransactionDTO transactionDTO, @PathVariable Long id)
	{
		transactionService.update(userDetails, transactionDTO, id);
	}

}
