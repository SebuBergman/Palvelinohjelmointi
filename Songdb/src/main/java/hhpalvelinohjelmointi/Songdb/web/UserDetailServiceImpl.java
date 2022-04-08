package hhpalvelinohjelmointi.Songdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hhpalvelinohjelmointi.Songdb.domain.User;
import hhpalvelinohjelmointi.Songdb.domain.UserRepository;

// This class is used by spring security to authenticate and authorize the user
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository repository;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.repository = userRepository;
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currentuser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currentuser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(currentuser.getRole()));
		return user;
	}
}