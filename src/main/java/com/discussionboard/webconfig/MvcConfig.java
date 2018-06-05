package com.discussionboard.webconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configurable
@EnableWebSecurity

public class MvcConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService UserDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(UserDetailsService).passwordEncoder(getPasswordEncoder());
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**").allowedOrigins("http://localhost:4200");
	          
	        }
	    };
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
	
		.authorizeRequests()
	
		.antMatchers("/user/register","/user/login","/logout","/api/topic/**","/post","/getallfiles").permitAll()
		
		.anyRequest().fullyAuthenticated().and()
//		.formLogin().loginPage("/user/login").and()
        
		.logout()
        .permitAll()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
        .and()
		
		.httpBasic().and()
		
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
		
		.csrf().disable();
	}
	
	 private PasswordEncoder getPasswordEncoder() {
	        return new PasswordEncoder() {
	            @Override
	            public String encode(CharSequence charSequence) {
	                return charSequence.toString();
	            }

	            @Override
	            public boolean matches(CharSequence charSequence, String s) {
	                return true;
	            }
	        };
	    }

}