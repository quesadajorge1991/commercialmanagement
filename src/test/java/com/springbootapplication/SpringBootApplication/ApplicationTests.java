package com.springbootapplication.SpringBootApplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.SecurityFilterChain;

import com.springbootapplication.SpringBootApplication.Entity.Proveedor;
import com.springbootapplication.SpringBootApplication.Repository.ProovedorRepository;

@SpringBootTest
class ApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	ProovedorRepository proovedorRepository;

	@Test
	void contextLoads() throws SQLException {

		List<Proveedor> list = proovedorRepository.findByOrderByVigencia();

		for (int i = 0; i < list.size(); i++) {

			System.out.println(list.get(i).getAliasProveedor());
		}

	}

}
