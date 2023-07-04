package ru.netology.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

class MobileBankApiTestV2 {
    @Test
    void shouldReturnDemoAccounts() {
      // Given - When - Then
      // Предусловия
      given()
          .baseUri("http://localhost:9999/api/v1")
      // Выполняемые действия
      .when()
          .get("/demo/accounts")
      // Проверки
      .then()
          .statusCode(200)
          // .header("Content-Type", "application/json; charset=UTF-8")
          // специализированные проверки - лучше
          .contentType(ContentType.JSON)
              .body("every{it.balance>=0}", is(true))
              .body("", hasSize(3))
              .body("[1].currency", equalTo("USD"))
              .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
              .body("[0].currency", equalTo("RUB"));
      ;
    }
}
