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
import com.springbootapplication.SpringBootApplication.Repository.PuebloRepository;
import com.springbootapplication.SpringBootApplication.Repository.ServicioRepository;
import com.springbootapplication.SpringBootApplication.Repository.TipoContratoRepository;
import com.springbootapplication.SpringBootApplication.Repository.UsersRepository;
import com.springbootapplication.SpringBootApplication.Services.MunicipioService;
import com.springbootapplication.SpringBootApplication.Services.UsersService;

@SpringBootTest
class ApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	PuebloRepository puebloRepository;

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	ProovedorRepository proovedorRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	TipoContratoRepository tipoContratoRepository;

	@Autowired
	UsersService service;

	@Autowired
	ServicioRepository servicioRepository;

	@Autowired
	MunicipioService municipioService;

	@Test
	void contextLoads() throws SQLException {

		// System.out.println(puebloRepository.findById(1L).get().getNomb_pueb());

		// System.out.println(usersRepository.findByUsernamee("as").getDescripcion());

		// servicioRepository.deleteservicio(10);

	//	municipioService.getMunicipiosByProvincia(1).forEach(item -> System.out.println(item.getNomb_mun()));

	}

}
