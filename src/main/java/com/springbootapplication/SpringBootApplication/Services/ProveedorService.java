package com.springbootapplication.SpringBootApplication.Services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.Proveedor;
import com.springbootapplication.SpringBootApplication.Repository.ProovedorRepository;
import com.springbootapplication.SpringBootApplication.Services.IProveedorService.IProveedorService;

@Service
public class ProveedorService implements IProveedorService{

	@Autowired
	ProovedorRepository proovedorRepository;

	public List<Proveedor> findByOrderByVigencia() {
		return proovedorRepository.findByOrderByVigencia();
	}

	public List<Proveedor> findByOrderByNroRegistro() {
		return proovedorRepository.findByOrderByNroRegistro();
	}

	public Proveedor findById(int id) {
		return proovedorRepository.findById(id).get();
	}

	public Proveedor save(Proveedor proveedor) {
		return proovedorRepository.save(proveedor);
	}

	// deleteById

	public void deleteById(int id) {
		proovedorRepository.deleteById(id);
	}
	
	
	public  int diasRestantes(Date fecha) {
		DateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
		int dias = 0;
		boolean activo = false;
		Calendar calendar;
		Date aux;
		do {
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, dias);
			aux = calendar.getTime();
			if (dd.format(aux).equals(dd.format(fecha))) {

				activo = true;
			} else {
				dias++;
			}
		} while (activo != true);
		return dias; 
	
	}
	
	
	/* utilizar este metodo cuando en las entidades se cambie de el paquete util al el time para las fechas en futuras versiones*/
/*	public int diasRestantes1(String inputDate) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        LocalDate currentDate = LocalDate.now();
	        LocalDate targetDate = LocalDate.parse(inputDate, formatter);
	        int daysUntil = (int) ChronoUnit.DAYS.between(currentDate, targetDate);
	        return daysUntil;
		
	} */
	
	

	
	

}
