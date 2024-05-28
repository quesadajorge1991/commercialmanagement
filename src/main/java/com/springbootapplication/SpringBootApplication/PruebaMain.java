package com.springbootapplication.SpringBootApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.springbootapplication.SpringBootApplication.Services.ProveedorService;

public class PruebaMain {

	public static void main(String[] args) throws ParseException {
		ProveedorService service = new ProveedorService();
		DateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
		Date date = dd.parse("28-05-2024");
		System.out.println(service.diasRestantes(date));

	}

}
