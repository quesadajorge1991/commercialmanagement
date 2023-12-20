package com.springbootapplication.SpringBootApplication.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.Authorities;
import com.springbootapplication.SpringBootApplication.Repository.AuthoritiesRepository;

@Service
public class AuthoritiesService {

	@Autowired
	AuthoritiesRepository authoritiesRepository;

	public Authorities save(Authorities authorities) {
		return authoritiesRepository.save(authorities);
	}

	public void deleteAuthoritiesByUsername(int id) {
		authoritiesRepository.deleteAuthoritiesByUsername(id);

	}

}
