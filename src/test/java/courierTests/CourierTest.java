package courierTests;

import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierClient;
import org.example.courier.CourierCreds;
import org.example.courier.RandomCourier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class CourierTest {

    Courier courier;
    CourierClient courierClient;

    @Before
    public void setUp() {
        courier = RandomCourier.getRandomCourier();
        courierClient = new CourierClient();
    }

    @Test
    public void createCourier() {
        ValidatableResponse response = courierClient.create(courier);
        response.statusCode(201).and().assertThat().body("ok", equalTo(true));

        response = courierClient.login(new CourierCreds(courier));
        courierClient.delete(response.extract().path("id").toString());
    }

    @Test
    public void cannotCreateIdenticalCouriers() {
        ValidatableResponse response = courierClient.create(courier);
        ValidatableResponse response2 = courierClient.create(courier);
        response2.statusCode(409);
    }

    @Test
    public void loginIsRequariedToCreate() {
        ValidatableResponse response = courierClient.create(courier.setLogin(null));
        response.statusCode(400);
    }

    @Test
    public void passwordIsRequariedToCreate() {
        ValidatableResponse response = courierClient.create(courier.setPassword(null));
        response.statusCode(400);
    }

    @Test
    public void nameIsRequariedToCreate() {
        ValidatableResponse response = courierClient.create(courier.setFirstName(null));
        response.statusCode(400);
        courierClient.delete(response.extract().path("id").toString());
    }
}
