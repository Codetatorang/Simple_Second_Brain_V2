package ibf2022.tfip.simplesecondbrain.Server.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ibf2022.tfip.simplesecondbrain.Server.repositories.UserDao;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

        private final JwtFilter jwtAuthFilter;
        private final UserDao userDaoRepo;


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf((csrf) -> csrf.disable())
                                .authorizeHttpRequests((auth) -> auth
                                                .requestMatchers("**")
                                                .permitAll()
                                                .anyRequest()
                                                .authenticated())
                                .authenticationProvider(authenticationProvider())
                                .sessionManagement((session) -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
                final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
                authenticationProvider.setUserDetailsService(userDetailsService());
                authenticationProvider.setPasswordEncoder(passwordEncoder());
                return authenticationProvider;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                return new UserDetailsService() {
                        @Override
                        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                                return userDaoRepo.findUserbyUserName(username);
                        }
                };
        }
}
