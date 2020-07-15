package br.com.moneyup.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moneyup.dto.TransactionDTO;

@Service
public class TransactionService
{

	@Autowired
	private ModelMapper modelMapper;

	public void create(TransactionDTO transactionDTO)
	{

	}

}
