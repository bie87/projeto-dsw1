package br.ufscar.dc.dsw1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(
							"/", "/home", "/login",
							"/clientes/cadastrar", "/clientes/salvar",
							"/lojas/cadastrar", "/lojas/salvar",
							"/css/**", "/js/**", "/images/**"
						).permitAll()
						.requestMatchers("/cliente/**").hasRole("CLIENTE")
						.requestMatchers("/loja/**").hasRole("LOJA")
						.anyRequest().authenticated())
				.formLogin(login -> login
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.usernameParameter("email")
						.passwordParameter("senha")
						.successHandler(authenticationSuccessHandler())
						.permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll())
				.httpBasic(AbstractHttpConfigurer::disable);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword != null && encodedPassword.contentEquals(rawPassword);
			}
		};
	}
@Bean
public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return (request, response, authentication) -> {

        boolean cliente = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"));

        boolean loja = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_LOJA"));

        if (cliente) {
            response.sendRedirect("/cliente/home");
        } else if (loja) {
            response.sendRedirect("/loja/home");
        } else {
            response.sendRedirect("/");
        }
    };
  }
}
