package lms.exceptions;

import lms.entities.Instructor;
import lms.entities.Student;
import lms.repository.InstructorRepo;
import lms.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

/**
 * @author Mukhammed Asantegin
 */

@Configuration
@EnableWebSecurity
@Service
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SpringSecurity {
    private final StudentRepo studentRepo;
    private final InstructorRepo instructorRepo;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(request -> {
//            request
//                    .requestMatchers("/auth/api/**")
//                    .permitAll()
//                    .requestMatchers("/api/admin/**")
//                    .hasRole("ADMIN")
//                    .anyRequest()
//                    .authenticated();
//        });
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.httpBasic(Customizer.withDefaults());
//        return http.build();
        http.authorizeHttpRequests(request -> {
            request
                    .requestMatchers("/auth/api/**").permitAll()
//                    .requestMatchers("/company/api/**").hasRole("ADMIN")
//                    .requestMatchers("/course/api/**").hasRole("ADMIN")
//                    .requestMatchers("/group/api/**").hasRole("ADMIN")
//                    .requestMatchers("/inst/api/**").hasRole("ADMIN")
//                    .requestMatchers("/lesson/api/**").hasRole("ADMIN")
//                    .requestMatchers("/student/api/**").hasRole("ADMIN")
//                    .requestMatchers("/task/api/**").hasRole("ADMIN")
                    .anyRequest().authenticated();
        });
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return email -> (UserDetails) studentRepo.findByEmailForSec(email).orElseThrow(() ->
                new UsernameNotFoundException("User with email: " + email + " not exists"));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }


}
