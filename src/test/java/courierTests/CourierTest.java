package courierTests;

import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierClient;
import org.example.courier.CourierCreds;
import org.example.courier.RandomCourier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;

public class CourierTest {

    Courier courier;
    CourierClient courierClient;
    List<String> couriersId = new ArrayList<String>();

    public void addCourierId() {
        ValidatableResponse loginResponse = courierClient.login(new CourierCreds(courier));
        if (loginResponse.extract().statusCode() == 200) {
            couriersId.add(loginResponse.extract().path("id").toString());
        }
    }

    @Before
    public void setUp() {
        courier = RandomCourier.getRandomCourier();
        courierClient = new CourierClient();
    }

    @Test
    public void createCourier() {
        ValidatableResponse response = courierClient.create(courier);
        response.statusCode(201).and().assertThat().body("ok", equalTo(true));
        addCourierId();
    }

    @Test
    public void cannotCreateIdenticalCouriers() {
        courierClient.create(courier);
        addCourierId();

        ValidatableResponse createCourierResponse = courierClient.create(courier);
        addCourierId();
        createCourierResponse.statusCode(409);
    }

    @Test
    public void loginIsRequariedToCreate() {
        ValidatableResponse createCourierResponse = courierClient.create(courier.setLogin(null));
        addCourierId();
        createCourierResponse.statusCode(400);
    }

    @Test
    public void passwordIsRequariedToCreate() {
        ValidatableResponse createCourierResponse = courierClient.create(courier.setPassword(null));
        addCourierId();
        createCourierResponse.statusCode(400);
    }

    @Test
    public void nameIsRequariedToCreate() {
        ValidatableResponse createCourierResponse = courierClient.create(courier.setFirstName(null));
        addCourierId();
        createCourierResponse.statusCode(400);
    }

    @After
    public void tearDown() {
        for (String id: couriersId) {
            courierClient.delete(id);
        }
    }
}
