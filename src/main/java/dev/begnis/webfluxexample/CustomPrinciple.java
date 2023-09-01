package dev.begnis.webfluxexample;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to store the user information from the current logged-in user.
 *
 * @author Dimitrios Begnis
 */
@Getter
@Setter
public class CustomPrinciple extends JwtAuthenticationToken {

    /* Custom fields */
    private String userId;

    public CustomPrinciple(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
        super(jwt, authorities);
    }

}
