package unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.checkout.domain.entity.FreightCalculator;
import org.example.checkout.domain.entity.Product;
import org.junit.jupiter.api.Test;

public class FreightCalculatorTest {
	@Test
	public void shouldCalculateFreightForProductWithQuantity1() {
		Product product = new Product(6, "A", 1000, 100, 30, 10, 3, "USD");
		double freight = FreightCalculator.calculate(product, 1);
		assertEquals(30, freight);
	}

	@Test
	public void shouldCalculateFreightForProductWithQuantity3() {
		Product product = new Product(6, "A", 1000, 100, 30, 10, 3, "USD");
		double freight = FreightCalculator.calculate(product, 3);
		assertEquals(90, freight);
	}

	@Test
	public void shouldCalculateFreightForProductWithMinimumPrice() {
		Product product = new Product(6, "C", 1000, 10, 10, 10, 0.9, "USD");
		double freight = FreightCalculator.calculate(product, 1);
		assertEquals(10, freight);
	}
}
