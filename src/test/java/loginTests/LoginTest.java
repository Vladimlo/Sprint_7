package loginTests;

import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierClient;
import org.example.courier.CourierCreds;
import org.example.courier.RandomCourier;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;

public class LoginTest {

    Courier courier;
    CourierClient courierClient;

    @Before
    public void setUp() {
        courier = RandomCourier.getRandomCourier();
        courierClient = new CourierClient();
    }

    @Test
    public void courierLogin() {
        courierClient.create(courier);
        ValidatableResponse response = courierClient.login(new CourierCreds(courier));
        response.statusCode(200).and().body("id", notNullValue());
    }

    @Test
    public void loginIsRequariedToAuth() {
        ValidatableResponse response = courierClient.login(new CourierCreds(courier.setLogin(null)));
        response.statusCode(400);
    }

    @Test
    public void passwordIsRequariedToAuth() {
        ValidatableResponse response = courierClient.login(new CourierCreds(courier.setPassword(null)));
        response.statusCode(400);
    }

    @Test
    public void courierNotExist() {
        ValidatableResponse response = courierClient
                .login(new CourierCreds(courier.setLogin(courier.getLogin() + "notExist")));
        response.statusCode(404);
    }
}
