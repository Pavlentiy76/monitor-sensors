package com.example.monitorsensors.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("user"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/v2/api-docs",
                        "/v3/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html").authenticated()
                .antMatchers(HttpMethod.GET,"/sensors/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/sensors").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/sensors/search/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/sensors").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/sensors/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/sensors/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(swaggerAuthenticationEntryPoint())
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.GET.toString())).invalidateHttpSession(true);
    }

    @Bean
    public BasicAuthenticationEntryPoint swaggerAuthenticationEntryPoint() {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("Swagger Realm");
        return entryPoint;
    }
}
