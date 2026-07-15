package com.example.E_Commerce.configuration;
import com.example.E_Commerce.filters.JwtAuthFilter;
import com.example.E_Commerce.filters.OAuth2LoginFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private OAuth2LoginFilter oAuth2LoginFilter;
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http.authorizeHttpRequests(auth ->
               auth.requestMatchers("/api/auth/**").permitAll()
                       .anyRequest().authenticated())
               .oauth2Login(oauth2 -> oauth2.successHandler(oAuth2LoginFilter))
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                       .csrf(csrf -> csrf.disable())
                               .exceptionHandling(exception -> exception
                                       .authenticationEntryPoint((request, response, authException) -> {
                                           response.setContentType("application/json");
                                           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                           response.getWriter().write("{\n" +
                                                   " \"error\" : \"access denied\"" +
                                                   "}");
                                       }));
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder)
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);
    }
}
