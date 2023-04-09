package org.example.order;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient {

    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    private static final String PATH = "/api/v1/orders";

    public OrderClient() {
        RestAssured.baseURI = BASE_URI;
    }

    public ValidatableResponse create(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(PATH)
                .then();
    }

    public ValidatableResponse getOrderPage() {
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get(PATH)
                .then();
    }

}
