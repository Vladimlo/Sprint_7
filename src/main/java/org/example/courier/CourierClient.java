package org.example.courier;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient {

    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    private static final String CREATE_PATH = "api/v1/courier";
    private static final String LOGIN_PATH = "api/v1/courier/login";


    public CourierClient() {
        RestAssured.baseURI = BASE_URI;
    }

    public ValidatableResponse create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(CREATE_PATH)
                .then();
    }

    public ValidatableResponse login(CourierCreds creds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(creds)
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    public void delete(String id) {
        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"id\": \"" + id + "\"}")
                .when()
                .delete(CREATE_PATH)
                .then();
    }

}
