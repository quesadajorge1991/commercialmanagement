package com.springbootapplication.SpringBootApplication.SecurityConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity // habilita la seguridad
@EnableMethodSecurity(securedEnabled = true) // habilita la seguridad a nivel de metodos
@Configuration // le dice a spring que es una clase de configuracion
public class WebSecurityConfiguration {

	/* /*auth.anyRequest().authenticated(); */

	@Autowired
	DataSource dataSource;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().headers().frameOptions().disable().and()
				.authorizeHttpRequests(auth -> auth.requestMatchers("/**", "/templates/templateBase/**").permitAll().requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()

				).formLogin(

						formlogin -> {
							try {
								formlogin.loginPage("/login").permitAll().loginProcessingUrl("/logincheck");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				).logout().logoutUrl("/logout") .logoutSuccessUrl("/index").invalidateHttpSession(true).
				and()

				.build();

	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select usernamee,password,enabled from users where usernamee = ?")
				.authoritiesByUsernameQuery(
						"select username,authority from authorities INNER JOIN   users on  authorities.username=users.id where users.usernamee=? ");

		/*
		 * .groupAuthoritiesByUsername(
		 * "select g.id, g.group_name, ga.authority from groups g, group_members gm, group_authorities ga where gm.usernamee = ? and g.id = ga.group_id and g.id = gm.group_id"
		 * );
		 */
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
