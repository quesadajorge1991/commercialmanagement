package com.springbootapplication.SpringBootApplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootTest
class ApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	ApplicationContext applicationContext;

	SecurityFilterChain chain;

	@Test
	void contextLoads() throws SQLException {

		String sql = "select * from users";
		PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);

		ResultSet rs = preparedStatement.executeQuery();

		/*
		 * while (rs.next()) { System.out.println(rs.getString("nombre")); }
		 */

		String temp[] = applicationContext.getBeanDefinitionNames();

		// System.out.println(temp.length);

		for (String nombre : temp) {

			System.out.println(nombre);

		}

	}

}
