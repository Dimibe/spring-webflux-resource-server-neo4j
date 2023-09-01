package dev.begnis.webfluxexample;

import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.neo4j.driver.Driver;
import org.neo4j.driver.MetricsAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.neo4j.ConfigBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableReactiveNeo4jAuditing;
import org.springframework.data.neo4j.core.ReactiveDatabaseSelectionProvider;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableReactiveNeo4jRepositories;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@SpringBootApplication
@EnableReactiveNeo4jAuditing
@EnableReactiveNeo4jRepositories
public class ExampleApplication {

    public static final String SECURITY_SCHEME = "bearer-key";

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    /**
     * This is needed to make sure that the reactive transaction manager is used.
     */
    @Bean
    public ReactiveNeo4jTransactionManager reactiveTransactionManager(Driver driver,
                                                                      ReactiveDatabaseSelectionProvider provider) {
        return new ReactiveNeo4jTransactionManager(driver, provider);
    }

    /**
     * This is needed to make sure that the cypher queries are generated using the dialect for Neo4j 5.0.
     */
    @Bean
    public Configuration cypherConfiguration() {
        return Configuration.newConfig().withDialect(Dialect.NEO4J_5).build();
    }

    /**
     * This is needed to make sure that the micrometer metrics for Neo4j are exposed.
     */
    @Bean
    public ConfigBuilderCustomizer configBuilderCustomizer() {
        return configBuilder -> configBuilder.withMetricsAdapter(MetricsAdapter.MICROMETER);
    }

}
