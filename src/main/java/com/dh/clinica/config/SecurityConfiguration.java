package com.dh.clinica.config;

import com.dh.clinica.service.impl.auth.UsuarieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private /*final*/ UsuarieService usuarieService;
    @Autowired
    private /*final*/ BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/odontologos/**", "/pacientes/**")
                .hasAuthority("ADMIN")
                .antMatchers("/odontologoAlta.html", "/pacienteAlta.html", "/odontologoList.html","/pacienteList.html" )
                .hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403.html");*/


        /*http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/turnos/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/pacientes/**").hasRole("ADMIN")
                .antMatchers("/odontologos/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().logout();*/

        http.csrf()
                .disable()
                .authorizeRequests()
                    //.antMatchers("/odontologos/", "/pacientes/**", "/turnos/**").hasAuthority("USER")
                    //.antMatchers("/odontologos/**", "/pacientes/**", "/turnos/**").hasAuthority("ADMIN")
                    /*.antMatchers("/index.html",
                            "/turnoAlta.html",
                            "/turnoList.html")
                        .hasAuthority("USER")*/
                    .antMatchers(//"/index.html",
                            "/odontologoAlta.html",
                            "/pacienteAlta.html",
                            //"/turnoAlta.html",
                            //"/usuarieAlta.html",
                            "/odontologoList.html",
                            "/pacienteList.html"
                            //"/usuarieList.html",
                            /*"/turnoList.html"*/)
                        .hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and().formLogin()
                        .permitAll()
                    .and().logout()
                        .logoutSuccessUrl("/login")
                        .permitAll()
                        .and()
                    .exceptionHandling().accessDeniedPage("/403.html");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(usuarieService);
        return provider;
    }

    /*public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, UsuarieService usuarieService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usuarieService = usuarieService;
    }

    public SecurityConfiguration(boolean disableDefaults, BCryptPasswordEncoder bCryptPasswordEncoder, UsuarieService usuarieService) {
        super(disableDefaults);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usuarieService = usuarieService;
    }*/
}
