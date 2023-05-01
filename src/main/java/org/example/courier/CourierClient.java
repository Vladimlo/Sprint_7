package org.example.courier;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.config.AppConfig;

import static io.restassured.RestAssured.given;

public class CourierClient {

    private static final String CREATE_PATH = "api/v1/courier";
    private static final String LOGIN_PATH = "api/v1/courier/login";


    public CourierClient() {
        RestAssured.baseURI = AppConfig.BASE_URI;
    }

    @Step("Создание курьера")
    public ValidatableResponse create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(CREATE_PATH)
                .then();
    }

    @Step("Авторизация курьера")
    public ValidatableResponse login(CourierCreds creds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(creds)
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    @Step("Удаление курьера")
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
