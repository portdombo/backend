package com.portdombo.backend.infrastructure.api.controller;

import com.portdombo.backend.infrastructure.api.dto.CreateTechnologyRequest;
import com.portdombo.backend.infrastructure.mocks.TechnologyMocksFactory;
import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;
import com.portdombo.backend.infrastructure.persistence.repository.TechnologyRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TechnologyControllerTests {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("portdombo")
            .withUsername("postgres")
            .withPassword("postgres");

    @Autowired
    TechnologyRepository technologyRepository;

    private String BASE_URL = "/api/v1/";

    @LocalServerPort
    private Integer port;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        technologyRepository.deleteAll();
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    @DisplayName("Should return 400 if name is null or empty")
    void shouldReturn400IfNameIsNull(String name) {
        CreateTechnologyRequest request = TechnologyMocksFactory.createTechnologyRequestFactory();
        request.setName(name);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(BASE_URL + "technologies")
                .then()
                .statusCode(400)
                .body("data", equalTo("Name is required!"));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    @DisplayName("Should return 400 if description is null or empty")
    void shouldReturn400IfDescriptionIsNull(String description) {
        CreateTechnologyRequest request = TechnologyMocksFactory.createTechnologyRequestFactory();
        request.setDescription(description);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(BASE_URL + "technologies")
                .then()
                .statusCode(400)
                .body("data", equalTo("Description is required!"));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    @DisplayName("Should return 400 if image is null or empty")
    void shouldReturn400IfImageIsNull(String imageURL) {
        CreateTechnologyRequest request = TechnologyMocksFactory.createTechnologyRequestFactory();
        request.setImage(imageURL);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(BASE_URL + "technologies")
                .then()
                .statusCode(400)
                .body("data", equalTo("ImageURL is required!"));
    }

    @Test
    @DisplayName("Should return 409 if technology already exists")
    void shouldReturn409IfTechnologyAlreadyExists() {
        CreateTechnologyRequest request = TechnologyMocksFactory.createTechnologyRequestFactory();
        TechnologyEntity entity =  TechnologyMocksFactory.toTechnologyEntityFactory(request);
        technologyRepository.save(entity);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(BASE_URL + "technologies")
                .then()
                .statusCode(409)
                .body("data", equalTo("Technology already exists"));
    }
}
