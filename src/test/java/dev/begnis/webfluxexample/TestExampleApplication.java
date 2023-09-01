package dev.begnis.webfluxexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.Neo4jContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestExampleApplication {

    public static void main(String[] args) {
        SpringApplication.from(ExampleApplication::main).with(TestExampleApplication.class).run(args);
    }

    @Bean
    @RestartScope
    @ServiceConnection
    public Neo4jContainer<?> neo4jContainer() {
        return new Neo4jContainer<>("neo4j:5").withoutAuthentication();
    }

}
