package com.springbootapplication.SpringBootApplication.Repository;

import com.springbootapplication.SpringBootApplication.Entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	Users findByUsernamee(String username);

}
