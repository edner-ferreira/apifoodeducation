package br.com.apifoodeducation.configuration;

//import br.com.apifoodeducation.service.UsuarioDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests(authorizeRequests ->
//                    authorizeRequests
//                            .requestMatchers("/**").permitAll()
////                            .requestMatchers("/admin/**").hasRole("ADMIN")
////                            .requestMatchers("/medico/**").hasAnyRole("ADMIN", "MEDICO")
//                            .anyRequest().authenticated()
////            )
////            .formLogin(formLogin ->
////                    formLogin
////                            .loginPage("/login")
////                            .permitAll()
////            )
////            .logout(logout ->
////                    logout
////                            .permitAll()
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
