package my.iium.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import my.iium.hr.security.SecurityUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public UserDetailsService userDetailsService() {
		return new SecurityUserDetailsService();
	};

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	// having a role
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// change the hasAuthority depending on the role requirements
		// later the code below is much easier to maintain so it should be implemented
		// -ahmad
		http.authorizeRequests().antMatchers("/js/**", "/css/**", "/img/**", "/register", "/UserRegistration")
				.permitAll().antMatchers("/**").hasAnyAuthority("User", "Admin")
				.antMatchers("/filingCode/**", "/Template/**", "/UserRole/**").hasAuthority("Admin").anyRequest()
				.authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll().and()
				.exceptionHandling().accessDeniedPage("/access_denied");

	}
	// IMPLEMENT THIS AFTER FINISHING THE PROJECT IT MAKES IT EASIER TO CONTROL THE
	// ROLES WITHOUT THYMELEAF
	/*
	 * .hasAnyAuthority("Admin")
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests() .antMatchers("/").hasAnyAuthority("USER", "CREATOR",
	 * "EDITOR", "ADMIN") .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
	 * .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
	 * .antMatchers("/delete/**").hasAuthority("ADMIN")
	 * .anyRequest().authenticated() .and() .formLogin().permitAll() .and()
	 * .logout().permitAll() .and() .exceptionHandling().accessDeniedPage("/403") ;
	 * }
	 */
}
