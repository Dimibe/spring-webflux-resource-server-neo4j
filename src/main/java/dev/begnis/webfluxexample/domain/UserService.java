package dev.begnis.webfluxexample.domain;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Set;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> findByProviderId(String providerId) {
        return userRepository.findByProviderId(providerId);
    }

    public Mono<User> createNewUser(String providerId, Jwt jwt) {
        var email = jwt.getClaimAsString("email");
        var name = jwt.getClaimAsString("name");

        var user = new User(providerId, name, email, Set.of(UserRole.USER));

        return userRepository.save(user);
    }
}
