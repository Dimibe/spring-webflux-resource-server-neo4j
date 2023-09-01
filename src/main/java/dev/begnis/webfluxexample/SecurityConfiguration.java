package dev.begnis.webfluxexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import dev.begnis.webfluxexample.domain.UserRole;

@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
                                                            AuthenticationConverter converter) {
        http.authorizeExchange(ex -> ex.pathMatchers("/actuator/**")
                                       .permitAll()
                                       .pathMatchers("/swagger-ui.html", "/webjars/swagger-ui/**", "/v3/api-docs/**")
                                       .permitAll()
                                       .pathMatchers("/api/common/**")
                                       .authenticated()
                                       .anyExchange()
                                       .hasAnyRole(UserRole.ADMIN.getRoleName()))
            .oauth2ResourceServer(oauth -> oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(converter)))
            .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }

    /**
     * This bean is used to set the current user as the auditor for the database.
     */
    @Bean
    public ReactiveAuditorAware<String> auditorAware() {
        return () -> ReactiveSecurityContextHolder.getContext().map(t -> t.getAuthentication().getName());
    }
}
