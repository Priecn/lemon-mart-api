package com.prince.security.config;

import com.prince.security.AppAuthenticationEntryPoint;
import com.prince.security.AppAuthorizationTokenFilter;
import com.prince.security.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AppAuthenticationEntryPoint entryPoint;
    private AppUserDetailsService userDetailsService;
    private AppAuthorizationTokenFilter appAuthorizationTokenFilter;
    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    public WebSecurityConfig(AppAuthenticationEntryPoint entryPoint, AppUserDetailsService userDetailsService, AppAuthorizationTokenFilter appAuthorizationTokenFilter) {
        this.entryPoint = entryPoint;
        this.userDetailsService = userDetailsService;
        this.appAuthorizationTokenFilter = appAuthorizationTokenFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(entryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/h2-console/**/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(appAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().sameOrigin().cacheControl(); //for H2 console
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, authenticationPath)
                .and().ignoring().antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js")
                .and().ignoring().antMatchers(HttpMethod.GET,"/h2-console/**/**");
    }
}

