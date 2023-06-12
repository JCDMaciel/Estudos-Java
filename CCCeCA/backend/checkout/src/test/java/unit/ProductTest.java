package unit;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.checkout.domain.entity.Product;
import org.junit.jupiter.api.Test;

public class ProductTest {
	@Test
	public void shouldNotCreateProductWithInvalidDimensions() {
		assertThrows(IllegalArgumentException.class, () ->
				new Product(1, "A", 1000, -10, -10, -10, -10, "BRL"));
	}
}
