package OrderTests;

import io.restassured.response.ValidatableResponse;
import org.example.order.Order;
import org.example.order.OrderClient;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final Order order;
    private final OrderClient orderClient;

    public CreateOrderTest(Order order) {
        this.order = order;
        orderClient = new OrderClient();
    }

    @Parameterized.Parameters
    public static Object[][] createOrderData() {
        return new Object[][]{
                {new Order(List.of("BLACK", "GREY"))},
                {new Order(List.of("BLACK"))},
                {new Order(List.of("GREY"))}
        };
    }

    @Test
    public void createOrder() {
        ValidatableResponse response = orderClient.create(order);
        response.statusCode(201).and().body("track", notNullValue());
    }

    @After
    public void deleteOrder() {

    }
}
