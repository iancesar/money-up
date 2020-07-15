package br.com.moneyup.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moneyup.dto.TransactionDTO;
import br.com.moneyup.dto.TransactionsDTO;
import br.com.moneyup.entity.Transaction;
import br.com.moneyup.entity.User;
import br.com.moneyup.repository.TransactionRepository;
import br.com.moneyup.repository.UserRepository;
import br.com.moneyup.security.CustomUserDetails;
import br.com.moneyup.util.Utils;

@Service
public class TransactionService
{

	@Autowired
	private ModelMapper				modelMapper;

	@Autowired
	private UserRepository			userRepository;

	@Autowired
	private TransactionRepository	transactionRepository;

	public void create(TransactionDTO transactionDTO, CustomUserDetails userDetails)
	{
		Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
		transaction.setUser(userRepository.getOne(userDetails.getUser().getId()));

		transactionRepository.save(transaction);
	}

	public List<TransactionsDTO> list(CustomUserDetails userDetails)
	{

		User user = userRepository.getOne(userDetails.getUser().getId());
		List<Transaction> transactions = transactionRepository.findByUser(user);

		Type listType = new TypeToken<List<TransactionsDTO>>()
		{
		}.getType();

		return modelMapper.map(transactions, listType);
	}

	public void delete(CustomUserDetails userDetails, Long id)
	{
		User user = userRepository.getOne(userDetails.getUser().getId());

		Optional<Transaction> transaction = transactionRepository.findByUserAndId(user, id);

		transaction.ifPresent(consumer -> {
			transactionRepository.delete(consumer);
		});

	}

	public void update(CustomUserDetails userDetails, TransactionDTO transactionDTO, Long id)
	{
		User user = userRepository.getOne(userDetails.getUser().getId());

		Optional<Transaction> transaction = transactionRepository.findByUserAndId(user, id);

		transaction.ifPresent(consumer -> {

			Transaction transactionToUpdate = modelMapper.map(transactionDTO, Transaction.class);

			BeanUtils.copyProperties(transactionToUpdate, consumer, Utils.getNullPropertyNames(transactionToUpdate));

			transactionRepository.save(consumer);

		});

	}

}
