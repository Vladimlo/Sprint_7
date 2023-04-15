package orderTests;

import io.restassured.response.ValidatableResponse;
import org.example.order.OrderClient;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;

public class GetOrdersTest {

    OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void getOrdersTest() {
        ValidatableResponse response = orderClient.getOrderPage();
        response.statusCode(200).assertThat().body("orders", notNullValue());
    }

}
