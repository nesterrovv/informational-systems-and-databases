package com.nesterrovv.isdbBackend.config;

import com.nesterrovv.isdbBackend.config.jwt.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JWTFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "/api/user/new-user", "/api/user/user").permitAll();
    }

//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public AuthenticationProvider authProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return provider;
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and()
//                // starts authorizing configurations
//                .authorizeRequests()
//                // ignoring the guest's urls "
//                .antMatchers("/", "/api/user/new-user", "/api/user/user").permitAll()
//                // authenticate all remaining URLS
//                .anyRequest().fullyAuthenticated().and()
//                /* "/logout" will log the user out by invalidating the HTTP Session,
//                 * cleaning up any {link rememberMe()} authentication that was configured, */.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll().clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/account/logout", "POST")).logoutSuccessUrl("/").and()
//                // enabling the basic authentication
//                .httpBasic().and()
//                // configuring the session on the server
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
//                // disabling the CSRF - Cross Site Request Forgery
//                .csrf().disable();
//    }
}