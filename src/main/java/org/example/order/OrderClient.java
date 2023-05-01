package org.example.order;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.config.AppConfig;

import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final String PATH = "/api/v1/orders";

    public OrderClient() {
        RestAssured.baseURI = AppConfig.BASE_URI;
    }

    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(PATH)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrderPage() {
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get(PATH)
                .then();
    }

}
