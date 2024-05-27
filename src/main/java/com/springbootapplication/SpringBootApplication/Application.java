package com.springbootapplication.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springbootapplication.SpringBootApplication.Controllers.UsersController;
import com.springbootapplication.SpringBootApplication.Entity.Authorities;
import com.springbootapplication.SpringBootApplication.Entity.Municipio;
import com.springbootapplication.SpringBootApplication.Entity.Provincia;
import com.springbootapplication.SpringBootApplication.Entity.Users;
import com.springbootapplication.SpringBootApplication.Repository.AuthoritiesRepository;
import com.springbootapplication.SpringBootApplication.Repository.MunicipioRepository;
import com.springbootapplication.SpringBootApplication.Repository.ProvinciaRepository;
import com.springbootapplication.SpringBootApplication.Repository.UsersRepository;
import com.springbootapplication.SpringBootApplication.Services.UsersService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	AuthoritiesRepository authoritiesRepository;

	@Autowired
	ProvinciaRepository provinciaRepository;

	@Autowired
	MunicipioRepository municipioRepository;
	
	@Autowired
	UsersService usersService;

	@PostConstruct
	public void insertBD() {
		UsersController u = new UsersController();

		List<Users> user = usersRepository.findAll();
		List<Provincia> provincias = (List<Provincia>) provinciaRepository.findAll();

		/* pregnta si existe el usuario en la bd */
		if (user.isEmpty()) {
			usersRepository.save(new Users("admin", u.encodePassword("admin"), true, "Admiistrador full del sistema"));
			String authorities[] = new String[] { "ROLE_ADMIN", "ROLE_CREATE", "ROLE_UPDATE", "ROLE_DELETE",
					"ROLE_COMERCIAL", "ROLE_ECONOMIA", "ROLE_METEOROLOGIA", "ROLE_PRONOSTICO", "ROLE_ESTACIONES" };
			for (int i = 0; i < authorities.length; i++) {
				authoritiesRepository
						.save(new Authorities(authorities[i], new Users(usersService.findByUsername("admin").getId())));
			}
			
			insertProvinciasMunicipios();

		} else {
			System.out.println("La bd ya contiene los user y las provincias");
		}

		

	}

	public void insertProvinciasMunicipios() {

		provinciaRepository.save(new Provincia("Sancti Spíritus"));

		String[] municipiosSS = { "Cabaiguan", "Fomento", "Yaguajay", "La Sierpe", "Jatibonico", "Taguasco",
				"Sancti Spiritus", "Trinidad" };

		List<Provincia> provincias = new ArrayList<>();

		provincias.add(new Provincia("Sancti Spíritus"));
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
		
		

		for (int i = 0; i < municipiosSS.length; i++) {
				municipioRepository.save(new Municipio(municipiosSS[i], new Provincia(provinciaRepository.findByNombProv("Sancti Spíritus").getId())));
			}

	}
}
