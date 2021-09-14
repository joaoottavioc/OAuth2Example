package com.oauth2example.endpoint.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwk.JwkProvider;


import lombok.RequiredArgsConstructor;

@Order(1)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.ignored}")
    private String nonSecureUrl;

    @Value("${keycloak.jwk}")
    private String jwkProviderUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .cors()
                .and()
                .addFilterBefore(
                        new AccessTokenFilter(
                                jwtTokenValidator(keycloakJwkProvider()),
                                authenticationManagerBean()),
                        BasicAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(nonSecureUrl);
    }

    @ConditionalOnMissingBean
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new KeycloakAuthenticationProvider();
    }

    @Bean
    public JwtTokenValidator jwtTokenValidator(JwkProvider jwkProvider) {
        return new JwtTokenValidator(jwkProvider);
    }

    @Bean
    public JwkProvider keycloakJwkProvider() {
        return new KeycloakJwkProvider(jwkProviderUrl);
    }
}



/*
@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	web.ignoring().antMatchers("/resources/**", "/customers");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.cors().and().csrf().disable()
	.authorizeRequests()
	.anyRequest()
	.anonymous();
	}

}*/
