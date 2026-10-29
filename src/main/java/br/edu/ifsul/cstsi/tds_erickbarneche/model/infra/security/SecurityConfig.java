package br.edu.ifsul.cstsi.tds_erickbarneche.model.infra.security;

import br.edu.ifsul.cstsi.tds_erickbarneche.model.infra.jwt.CustomJwtAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true) // habilita o @Secured nos controllers
public class SecurityConfig {

    private final CustomJwtAuthenticationConverter customJwtAuthenticationConverter;

    public SecurityConfig(CustomJwtAuthenticationConverter customJwtAuthenticationConverter) {
        this.customJwtAuthenticationConverter = customJwtAuthenticationConverter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {

        PathPatternRequestMatcher.Builder path = PathPatternRequestMatcher.withDefaults();

        http
                .csrf(csrf -> csrf.disable()) // API stateless não precisa de CSRF
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // sem sessão
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(
                            path.matcher("/v3/api-docs/**"),
                            path.matcher("/swagger-ui.html"),
                            path.matcher("/swagger-ui/**")
                    ).permitAll(); // documentação liberada
                    authorize.requestMatchers(path.matcher(HttpMethod.POST, "/api/v1/autenticacao/login")).permitAll(); // login liberado
                    authorize.anyRequest().authenticated(); // o resto exige token
                })
                // O Resource Server lê o header Authorization, valida o JWT (JwtDecoder) e monta a Authentication (converter)
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(customJwtAuthenticationConverter))
                );

        return http.build();
    }
}