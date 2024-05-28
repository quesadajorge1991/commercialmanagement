package com.springbootapplication.SpringBootApplication.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.springbootapplication.SpringBootApplication.Entity.Users;
import com.springbootapplication.SpringBootApplication.Repository.UsersRepository;
import com.springbootapplication.SpringBootApplication.Services.IUsersService.IUsersService;

@Service
public class UsersService implements IUsersService {

	@Autowired
	UsersRepository usersRepository;

	// paginated and ordered results
	public List<Users> findAll(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
		Page<Users> pageUsersPageable = usersRepository.findAll(pageable);
		if (pageUsersPageable.hasContent()) {
			return pageUsersPageable.getContent();
		} else {
			return new ArrayList<>();
		}

	}

	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	public Users findByUsername(String username) {
		return usersRepository.findByUsernamee(username);

	}

	public Users findById(int id) {
		return usersRepository.findById(id).get();
	}

	public Users save(Users user) {
		return usersRepository.save(user);
	}

	public void deleteById(int id) {
		usersRepository.deleteById(id);

	}

	@Override
	public String encodePassword(String password) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

}
