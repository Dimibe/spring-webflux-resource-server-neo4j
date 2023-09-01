package dev.begnis.webfluxexample;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import dev.begnis.webfluxexample.domain.User;
import dev.begnis.webfluxexample.domain.UserRole;
import dev.begnis.webfluxexample.domain.UserService;
import reactor.core.publisher.Mono;

/**
 * This class is used to convert the JWT token into a user object.
 *
 * @author Dimitrios Begnis
 */
@Component
public class AuthenticationConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    private final UserService userService;

    public AuthenticationConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {
        var providerId = jwt.getSubject();
        return userService.findByProviderId(providerId)
                          .switchIfEmpty(userService.createNewUser(providerId, jwt))
                          .map(user -> mapUserToAuthentication(user, jwt));
    }

    private CustomPrinciple mapUserToAuthentication(User user, Jwt jwt) {
        var authorities = user.getRoles().stream().map(UserRole::toAuthority).toList();
        var authentication = new CustomPrinciple(jwt, authorities);
        authentication.setUserId(user.getId());
        return authentication;
    }
}
