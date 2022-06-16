package com.zemoso.springbootproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.jdbcAuthentication()
                    .dataSource(dataSource)     //creates database connection
                    .usersByUsernameQuery("select username,password,enabled from users  where username=?")
                    .authoritiesByUsernameQuery("select username,authority from users where username=?")
                    .passwordEncoder(passwordEncoder);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests()
                    .antMatchers("/restaurant/home").permitAll()
                    .antMatchers("/restaurant/menu").permitAll()
                    .antMatchers("/restaurant/login").permitAll()
                    .antMatchers("/restaurant/loginHome").authenticated()
                    .antMatchers("/restaurant/saveItem").authenticated()
                    .antMatchers("/restaurant/chefMenu").hasAuthority("CHEF")
                    .antMatchers("/restaurant/updateAvailability").hasAuthority("CHEF")
                    .anyRequest().hasAuthority("ADMIN")

                    //.antMatchers("/menu/list").authenticated()
                    .and()
                    .formLogin()
//                    .loginPage("/restaurant/login")
                    .defaultSuccessUrl("/restaurant/loginHome",true)

                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/restaurant/accessDenied")
            ;

        }
    }
