package dev.begnis.webfluxexample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

@SpringBootTest
@ImportTestcontainers(ContainerConfiguration.class)
class ExampleApplicationTests {

	@Test
	void contextLoads() {
	}

}
