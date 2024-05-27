package com.springbootapplication.SpringBootApplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.SecurityFilterChain;

import com.springbootapplication.SpringBootApplication.Entity.Municipio;
import com.springbootapplication.SpringBootApplication.Entity.Proveedor;
import com.springbootapplication.SpringBootApplication.Entity.Provincia;
import com.springbootapplication.SpringBootApplication.Repository.MunicipioRepository;
import com.springbootapplication.SpringBootApplication.Repository.ProovedorRepository;
import com.springbootapplication.SpringBootApplication.Repository.ProvinciaRepository;
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
	
	@Autowired
	MunicipioRepository municipioRepository;
	
	@Autowired
	ProvinciaRepository provinciaRepository;

	@Test
	void contextLoads() throws SQLException {

		// System.out.println(puebloRepository.findById(1L).get().getNomb_pueb());

		// System.out.println(usersRepository.findByUsernamee("as").getDescripcion());

		// servicioRepository.deleteservicio(10);

		//municipioService.getMunicipiosByProvincia(1).forEach(item -> System.out.println(item.getNomb_mun()));
		
	insertProvinciasMunicipios();

	}
	
	public void insertProvinciasMunicipios() {

		provinciaRepository.save(new Provincia("Sancti Spíritus"));

		String[] municipiosSS = { "Cabaiguan", "Fomento", "Yaguajay", "La Sierpe", "Jatibonico", "Taguasco",
				"Sancti Spiritus", "Trinidad" };
		
		
		
		

		List<Provincia> provincias = new ArrayList<>();

		
		/*provincias.add(new Provincia("Pinar Del Río"));
		provincias.add(new Provincia("Artemisa"));
		provincias.add(new Provincia("La Habana"));
		provincias.add(new Provincia("Mayabeque"));
		provincias.add(new Provincia("Matanzas"));
		provincias.add(new Provincia("Villa Clara"));
		provincias.add(new Provincia("Cienfuegos"));
		provincias.add(new Provincia("Ciego de Ávila"));
		provincias.add(new Provincia("Camaguey"));
		provincias.add(new Provincia("Las Tunas"));
		provincias.add(new Provincia("Holguín"));
		provincias.add(new Provincia("Gramma"));
		provincias.add(new Provincia("Santiago de Cuba"));
		provincias.add(new Provincia("Guantánamo"));*/
		provincias.add(new Provincia("Sancti Spíritus"));
		
		

		for (int i = 0; i < municipiosSS.length; i++) {
			municipioRepository.save(new Municipio(municipiosSS[i],
					new Provincia(provinciaRepository.findByNombProv("Sancti Spíritus").getId())));
		}
	}
}
