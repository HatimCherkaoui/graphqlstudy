package com.hatimcherkaoui.graphqlstudy.it;

import com.hatimcherkaoui.graphqlstudy.GraphqlstudyApplication;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GraphqlstudyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookApiIntegrationTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("booksdb")
            .withUsername("postgres").withPassword("postgres");

    static {
        postgres.start();
    }

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @LocalServerPort
    int port;

    @BeforeEach
    void setUpRestAssured() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void contextLoads(ApplicationContext context) {
        assertThat(context).isNotNull();
    }

    @Test
    void testAddAndQuery() {
        // Admin can add
        RestAssured
                .given()
                .auth().preemptive().basic("admin", "admin123")
                .contentType("application/json")
                .body(Map.of("query", "mutation { addBook(title: \"Secured\", author: \"Bob\") { id } }"))
                .post("/graphql")
                .then().statusCode(200);

        // User can query
        RestAssured
                .given()
                .auth().preemptive().basic("user", "user123")
                .contentType("application/json")
                .body(Map.of("query", "{ books(author: \"Bob\") { title } }"))
                .post("/graphql")
                .then().statusCode(200);

        // User can't add (403)
        RestAssured
                .given()
                .auth().preemptive().basic("user", "user123")
                .contentType("application/json")
                .body(Map.of("query", "mutation { addBook(title: \"Hack\", author: \"Evil\") { id } }"))
                .post("/graphql")
                .then()
                .statusCode(200) // still 200
                .body("errors[0].message", containsString("Forbidden"));
    }
}