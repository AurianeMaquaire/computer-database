package com.excilys.main;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT username, password, enabled FROM user WHERE username = ?")
		.authoritiesByUsernameQuery("SELECT user_role.username username, role.name auhority FROM user_role JOIN role ON role.id = user_role.role_id WHERE user_role.username = ?")
		.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
	    .authorizeRequests()
		    .antMatchers("/AddComputer").hasAuthority("ADMIN")
			.antMatchers("/EditComputer").hasAuthority("ADMIN")
			.antMatchers("/").authenticated()
			.antMatchers("/Dashboard").authenticated()
			.antMatchers("/LoginProcess").permitAll()
		.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/LoginProcess")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/")
			.failureForwardUrl("/login?error")
		.and()
			.logout()
			.logoutSuccessUrl("/login?logout")
			.logoutUrl("/LogoutProcess")
			.deleteCookies("JSESSIONID");
	}

}