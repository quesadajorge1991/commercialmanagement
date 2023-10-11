package com.springbootapplication.SpringBootApplication.Controllers;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootapplication.SpringBootApplication.Entity.Authorities;
import com.springbootapplication.SpringBootApplication.Entity.TipoContrato;
import com.springbootapplication.SpringBootApplication.Entity.Users;
import com.springbootapplication.SpringBootApplication.Repository.*;

@Controller
public class UsersController {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	AuthoritiesRepository authoritiesRepository;

	public String encodePassword(String password) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	@GetMapping("/resetpass")
	public String resetpass(Model model) {
		model.addAttribute("users", usersRepository.findAll());
		return "Users/resetpass";
	}

	@GetMapping(value = "/getUsers")
	public String getUsers(Model model) {
		List<Users> usuarios = usersRepository.findAll();
		model.addAttribute("users", usuarios);
		return "templateBase/ComponentFragment :: users";

	}

	@PostMapping("/resetPassword")
	public @ResponseBody String resetPassword(@RequestParam(value = "username") int username,
			@RequestParam(value = "password") String password) {
		JSONObject jsono = new JSONObject();

		Users user = usersRepository.findById(username).get();

		System.err.println(user.getUsernamee());

		try {
			Users usuario = usersRepository.findById(username).get();
			usuario.setPassword(encodePassword(user.getPassword()));
			usuario.setEnabled(user.isEnabled());
			usuario.setDescripcion(user.getDescripcion());

			usersRepository.save(usuario);

			jsono.put("msgtype", "success");
			jsono.put("msgtitle", "Información");
			jsono.put("msgbody", "Se actualizó la contraseña al usuario" + user.getUsernamee());
		} catch (Exception e) {
			// TODO: handle exception
			jsono.put("msgtype", "error");
			jsono.put("msgtitle", "Error");
			jsono.put("msgbody", "Error al actualizar la contraseña al usuario" + user.getUsernamee());
		}

		return jsono.toString();
	}

	@GetMapping("/page403")
	public String page403() {
		return "page403";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/listusuarios")
	public String mostrarUsuarios(Model model) {
		List<Users> users = usersRepository.findAll();
		model.addAttribute("listusuarios", users);
		return "Users/Gestion_Usuario";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/addUsuario")
	public String addUsuario(Model model) {
		model.addAttribute("user", new Users());
		return "Users/addUsuario";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/delete_usuario/{id}")
	public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

		try {
			usersRepository.deleteById(id);
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Usuario eleminado correctamente ");
			return "redirect:/listusuarios";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eleminar el usuario " + e.getLocalizedMessage());
		}

		return "redirect:/listusuarios";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/add_Usuario")
	public String add_Usuario(@ModelAttribute("users") Users user, RedirectAttributes redirectAttributes) {

		try {

			Users usuario = new Users(user.getUsernamee(), encodePassword(user.getPassword()), user.isEnabled(),
					user.getDescripcion());
			usersRepository.save(usuario);
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Usuario agregado correctamente ");
			return "redirect:/listusuarios";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el usuario " + e.getLocalizedMessage());
		}

		return "redirect:/listusuarios";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/updateUsuario/{id}")
	public String update(Model model, @PathVariable(value = "id") int id) {
		Users usuarios = usersRepository.findById(id).get();
		model.addAttribute("usuario", usuarios);
		return "Users/updateUsuario";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/update_Usuario")
	public String update_Usuario(@ModelAttribute("usuario") Users user, RedirectAttributes redirectAttributes) {

		try {
			System.err.println(user.getUsernamee());
			System.out.println(usersRepository.getUsers(user.getUsernamee()).size());
			List<Users> list = usersRepository.getUsers(user.getUsernamee());
			if (list.size() == 1 || list.size() == 0) {

				Users usuario = usersRepository.findById(user.getId()).get();
				usuario.setUsernamee(user.getUsernamee());
				usuario.setEnabled(user.isEnabled());
				usuario.setDescripcion(user.getDescripcion());
				usersRepository.save(usuario);
				redirectAttributes.addFlashAttribute("msgbody",
						"Usuario " + usuario.getUsernamee() + " modificado correctamente");
				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");

			} else {
				redirectAttributes.addFlashAttribute("msgbody", "El nombre de usuario ya existe");
				redirectAttributes.addFlashAttribute("msgtipo", "error");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");
			}

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgbody", "Error al guardar los datos");
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
		}

		return "redirect:/listusuarios";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/changePassword")
	public @ResponseBody String changePasswordProfile(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {

		JSONObject jsono = new JSONObject();

		jsono.put("msgtype", "error");
		jsono.put("msgtitle", "Error");
		jsono.put("msgbody", "Error al actualizar la contraseña al usuario" );

		return jsono.toString();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/addPermisos")
	public String addPermisos(Model model) {
		List<Users> users = usersRepository.findAll();
		model.addAttribute("listusuarios", users);
		return "Authorities/addPermisos";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/getPermisos", produces = "application/json")
	public @ResponseBody List<Authorities> getPermisos(@RequestParam("id") int id) {
		Users user = usersRepository.findById(id).get();
		return user.getAuthoritiesList();

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/getPermisoss")
	public String getAuthorityUser(@RequestParam("usuario") int id, Model model) {
		Users user = usersRepository.findById(id).get();
		model.addAttribute("listusers", user);
		model.addAttribute("listauthorities", user.getAuthoritiesList());
		// model.addAttribute("listaAthoritiesInMemory",
		// permisosInMemory.getListinmemory());

		String permiso;
		for (int i = 0; i < user.getAuthoritiesList().size(); i++) {

			permiso = user.getAuthoritiesList().get(i).getAuthority();
			System.out.println(permiso);

			switch (permiso) {

			case "ROLE_ADMIN":
				if (!permiso.equalsIgnoreCase("ROLE_ADMIN")) {
					model.addAttribute("ROLE_ADMIN", false);

				} else {
					model.addAttribute("ROLE_ADMIN", true);
				}

			case "ROLE_CREATE":
				if (!permiso.equalsIgnoreCase("ROLE_CREATE")) {
					model.addAttribute("ROLE_CREATE", false);

				} else {
					model.addAttribute("ROLE_CREATE", true);
				}

			case "ROLE_UPDATE":
				if (!permiso.equalsIgnoreCase("ROLE_UPDATE")) {
					model.addAttribute("ROLE_UPDATE", false);

				} else {
					model.addAttribute("ROLE_UPDATE", true);
				}

			case "ROLE_DELETE":
				if (!permiso.equalsIgnoreCase("ROLE_DELETE")) {
					model.addAttribute("ROLE_DELETE", false);

				} else {
					model.addAttribute("ROLE_DELETE", true);
				}

			case "ROLE_COMERCIAL":
				if (!permiso.equalsIgnoreCase("ROLE_COMERCIAL")) {
					model.addAttribute("ROLE_COMERCIAL", false);

				} else {
					model.addAttribute("ROLE_COMERCIAL", true);
				}

			case "ROLE_ECONOMIA":
				if (!permiso.equalsIgnoreCase("ROLE_ECONOMIA")) {
					model.addAttribute("ROLE_ECONOMIA", false);

				} else {
					model.addAttribute("ROLE_ECONOMIA", true);
				}

			case "ROLE_METEOROLOGIA":
				if (!permiso.equalsIgnoreCase("ROLE_METEOROLOGIA")) {
					model.addAttribute("ROLE_METEOROLOGIA", false);

				} else {
					model.addAttribute("ROLE_METEOROLOGIA", true);
				}

			case "ROLE_PRONOSTICO":
				if (!permiso.equalsIgnoreCase("ROLE_PRONOSTICO")) {
					model.addAttribute("ROLE_PRONOSTICO", false);

				} else {
					model.addAttribute("ROLE_PRONOSTICO", true);
				}

			case "ROLE_ESTACIONES":
				if (!permiso.equalsIgnoreCase("ROLE_ESTACIONES")) {
					model.addAttribute("ROLE_ESTACIONES", false);

				} else {
					model.addAttribute("ROLE_ESTACIONES", true);
				}

			default: // model.addAttribute("ROLE_UPDATE", false); }

			}

		}
		return "templateBase/ComponentFragment :: contentAutorities";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addAuthorityToUser")
	public String addAuthorityToUser(@RequestParam(value = "usuario") int id,
			@RequestParam(value = "authorities[]", defaultValue = "") String authorities[],
			RedirectAttributes redirectAttrs, Model model) throws JSONException {

		try {
			Users usuario = usersRepository.findById(id).get();
			System.err.println(usuario.getId());

			authoritiesRepository
					.deleteByAuthorities(usuario.getId()); /* Para eliminar todos los permisos a el usuario */

			for (int i = 0; i < authorities.length; i++) {
				// verificar si existe el permiso que se kiere aÃ±adir

				String temp = authorities[i];

				// aÃ±adir el permiso
				try {
					System.err.println(temp);
					authoritiesRepository.save(new Authorities(authorities[i], usuario));
					redirectAttrs.addFlashAttribute("msgbody",
							"Se han insertado correctamente los permisos seleccionados al usuario "
									+ usuario.getUsernamee());
					redirectAttrs.addFlashAttribute("msgtitu", "Confirmacion");
					redirectAttrs.addFlashAttribute("msgtipo", "success");

				} catch (Exception e) {
					redirectAttrs.addFlashAttribute("msgbody", "Error al insertar permisos" + usuario.getUsernamee());
					redirectAttrs.addFlashAttribute("msgtitu", "Error");
					redirectAttrs.addFlashAttribute("msgtipo", "error");
				}

			}
			// Si la lista de permisos esta vacia muestra estos mensajes
			try {

				redirectAttrs.addFlashAttribute("msgbody",
						"Se han insertado correctamente los permisos seleccionados al usuario "
								+ usuario.getUsernamee());
				redirectAttrs.addFlashAttribute("msgtitu", "Confirmacion");
				redirectAttrs.addFlashAttribute("msgtipo", "success");

			} catch (Exception e) {
				redirectAttrs.addFlashAttribute("msgbody", "Error al insertar permisos" + usuario.getUsernamee());
				redirectAttrs.addFlashAttribute("msgtitu", "Error");
				redirectAttrs.addFlashAttribute("msgtipo", "error");
			}

		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("msgbody", "Error al intentar añadir los permisos ");
			redirectAttrs.addFlashAttribute("msgtitu", "Error");
			redirectAttrs.addFlashAttribute("msgtipo", "error");

		}

		return "redirect:/addPermisos";
	}

}
