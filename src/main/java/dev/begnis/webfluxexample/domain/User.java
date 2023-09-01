package dev.begnis.webfluxexample.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.Date;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Node("User")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;

    @CreatedDate
    Date createdDate;

    @LastModifiedDate
    Date updatedDate;

    @LastModifiedBy
    String updatedBy;

    private String providerId;

    @Getter
    private String name;

    private String email;

    Set<UserRole> roles;

    public User(final String providerId, final String name, final String email, Set<UserRole> roles) {
        this.providerId = providerId;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
}
