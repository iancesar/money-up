package br.com.moneyup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.moneyup.entity.User;
import br.com.moneyup.repository.UserRepository;
import br.com.moneyup.security.CustomUserDetails;

@Service
public class UserService implements UserDetailsService
{

	@Autowired
	private UserRepository userRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

		//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
		return new CustomUserDetails(user);
	}

}
