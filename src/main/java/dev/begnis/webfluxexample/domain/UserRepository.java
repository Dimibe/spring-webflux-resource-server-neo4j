package dev.begnis.webfluxexample.domain;

import reactor.core.publisher.Mono;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveNeo4jRepository<User, String> {

    @Query("MATCH (u:User { providerId: $providerId }) RETURN u")
    Mono<User> findByProviderId(@Param("providerId") String providerId);
}
