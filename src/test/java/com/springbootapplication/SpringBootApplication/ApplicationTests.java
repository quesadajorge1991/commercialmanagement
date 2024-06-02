package com.springbootapplication.SpringBootApplication;

import static org.mockito.ArgumentMatchers.booleanThat;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
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
import com.springbootapplication.SpringBootApplication.Services.ProveedorService;
import com.springbootapplication.SpringBootApplication.Services.ProvinciaService;
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

	@Autowired
	ProveedorService proovedorService;
	
	@Autowired
	ProvinciaService provinciaService;

	@Test
	void contextLoads() throws SQLException {

		// System.out.println(puebloRepository.findById(1L).get().getNomb_pueb());

		// System.out.println(usersRepository.findByUsernamee("as").getDescripcion());

		// servicioRepository.deleteservicio(10);

		// municipioService.getMunicipiosByProvincia(1).forEach(item ->
		// System.out.println(item.getNomb_mun()));

		// insertProvinciasMunicipios();

		// Date date=new Date(2024, 5, 27);

		// System.out.println("DIAS RESTANTES" + proovedorService.diasRestantes(date));

//System.out.println(	municipioService.listMunicipios().contains("Taguasco"));
		
		System.err.println(provinciaService.findByNombProv("Sancti Spiritus"));

	}

	boolean temp = false;

	public boolean muni(List<Municipio> listMunicipios) {

		for (int i = 0; i < listMunicipios.size(); i++) {
			if (listMunicipios.get(i).getNomb_mun().contains("Taguasco")) {
				temp = true;
				break;
			}

		}

		/*
		 * municipioService.findAll().forEach((item) -> {
		 * 
		 * if (item.getNomb_mun().contains("Taguasco")) { temp = true; break; }
		 * 
		 * }
		 * 
		 * );
		 */
		return temp;
	}

	public void insertProvinciasMunicipios() {

		provinciaRepository.save(new Provincia("Sancti Spíritus"));

		String[] municipiosSS = { "Cabaiguan", "Fomento", "Yaguajay", "La Sierpe", "Jatibonico", "Taguasco",
				"Sancti Spiritus", "Trinidad" };

		List<Provincia> provincias = new ArrayList<>();

		/*
		 * provincias.add(new Provincia("Pinar Del Río")); provincias.add(new
		 * Provincia("Artemisa")); provincias.add(new Provincia("La Habana"));
		 * provincias.add(new Provincia("Mayabeque")); provincias.add(new
		 * Provincia("Matanzas")); provincias.add(new Provincia("Villa Clara"));
		 * provincias.add(new Provincia("Cienfuegos")); provincias.add(new
		 * Provincia("Ciego de Ávila")); provincias.add(new Provincia("Camaguey"));
		 * provincias.add(new Provincia("Las Tunas")); provincias.add(new
		 * Provincia("Holguín")); provincias.add(new Provincia("Gramma"));
		 * provincias.add(new Provincia("Santiago de Cuba")); provincias.add(new
		 * Provincia("Guantánamo"));
		 */
		provincias.add(new Provincia("Sancti Spíritus"));

		for (int i = 0; i < municipiosSS.length; i++) {
			municipioRepository.save(new Municipio(municipiosSS[i],
					new Provincia(provinciaRepository.findByNombProv("Sancti Spíritus").getId())));
		}
	}
}
