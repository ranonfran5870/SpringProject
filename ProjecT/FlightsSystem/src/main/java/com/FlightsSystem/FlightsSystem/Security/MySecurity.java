package com.FlightsSystem.FlightsSystem.Security;


import com.FlightsSystem.FlightsSystem.Login.Permissions;
import com.FlightsSystem.FlightsSystem.jwt.JwtConfig;
import com.FlightsSystem.FlightsSystem.jwt.JwtTokenVerifier;
import com.FlightsSystem.FlightsSystem.jwt.JwtUserAPassAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurity extends WebSecurityConfigurerAdapter {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Autowired
    public MySecurity(JwtConfig jwtConfig, SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).
                usersByUsernameQuery("Select \"Username\",\"Password\",true from \"users\" where \"Username\" = ?")
                .authoritiesByUsernameQuery("select users.\"Username\",user_roles.\"Role_Name\" from \"user_roles\"\n" +
                        "JOIN \"users\" on user_roles.\"id\"=users.\"User_Role\"\n" +
                        "WHERE users.\"Username\" = ?").rolePrefix("ROLE_");

    }

    @Bean
    public PasswordEncoder getpassEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                addFilter(new JwtUserAPassAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey,jwtConfig),JwtUserAPassAuthenticationFilter.class)
                .authorizeRequests()
                //.antMatchers("/Airline/**").hasAnyRole(Permissions.AIRLINE.name(),Permissions.ADMIN.name())
                .antMatchers("/","/test/all","/css/*","/fonts/*","/images/*","/img/*","/js/","index").permitAll()
                .anyRequest().authenticated();



    }
}
