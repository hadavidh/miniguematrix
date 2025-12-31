package com.example.gematria.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GematriaApiIT {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void post_returns_results() {
        given()
          .contentType("application/json")
          .body("""
            {
              "text": "שלום",
              "methods": ["SIMPLE","SIDURI","ATBASH","ALBAM"]
            }
          """)
        .when()
          .post("/api/gematria")
        .then()
          .statusCode(200)
          .body("input", equalTo("שלום"))
          .body("results.size()", is(4))
          .body("results.find { it.method == 'SIMPLE' }.value", equalTo(376))
          .body("results.find { it.method == 'ATBASH' }.transformedText", equalTo("בכפי"));
    }

    @Test
    void get_simple_works() {
        given()
          .queryParam("text", "שלום")
          .queryParam("method", "SIMPLE")
        .when()
          .get("/api/gematria")
        .then()
          .statusCode(200)
          .body("results.size()", is(1))
          .body("results[0].method", equalTo("SIMPLE"))
          .body("results[0].value", equalTo(376));
    }
}
