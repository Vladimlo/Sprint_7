package org.example.courier;

public class RandomCourier {
    public static Courier getRandomCourier() {
        return new Courier("test" + 1000 + (int) (Math.random() * 10000),
                "test" + 1000 + (int) (Math.random() * 10000),
                "test" + 1000 + (int) (Math.random() * 10000));
    }
}
