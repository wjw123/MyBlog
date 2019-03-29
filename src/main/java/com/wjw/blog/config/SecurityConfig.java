package com.wjw.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.headers().frameOptions().disable();

		
		http.authorizeRequests()
		.antMatchers("/","/*","/article/*","/static/**","/comment/**").permitAll()
		.anyRequest().authenticated()
		.antMatchers("/admin/**").hasRole("admin")
		.and().formLogin().loginPage("/admin/login")
		.usernameParameter("account")
		.passwordParameter("password")
        .failureUrl("/admin/login")
        .defaultSuccessUrl("/admin/article")
		.permitAll();	
	
		/*		
		 http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/admin/login")
		.usernameParameter("333")
		.passwordParameter("333")
		.permitAll();*/
		
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
  //  auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    auth.inMemoryAuthentication().withUser("666").password("666").roles("admin");
    }
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/static/**");
    }
}
