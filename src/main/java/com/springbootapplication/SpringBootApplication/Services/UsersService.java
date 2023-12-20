package com.springbootapplication.SpringBootApplication.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.springbootapplication.SpringBootApplication.Entity.Users;
import com.springbootapplication.SpringBootApplication.Repository.UsersRepository;

@Service
public class UsersService {

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

}
