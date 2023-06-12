package org.example.checkout.domain.entity;

public class FreightCalculator {
    public static double calculate(Product product, int quantity) {
        double volume = product.getVolume();
        double density = product.getWeight() / volume;
        double itemFreight = 1000 * volume * (density / 100);
        return Math.max(itemFreight, 10) * quantity;
    }
}
