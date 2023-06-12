package unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.example.checkout.domain.entity.CurrencyTable;
import org.example.checkout.domain.entity.Order;
import org.example.checkout.domain.entity.Product;
import org.junit.jupiter.api.Test;

public class OrderTest {
	@Test
	public void shouldNotCreateOrderWithInvalidCpf() {
		UUID uuid = UUID.randomUUID();
		String cpf = "406.302.170-27";
		assertThrows(IllegalArgumentException.class, () -> new Order(uuid, cpf));
	}

	@Test
	public void shouldCreateEmptyOrder() {
		UUID uuid = UUID.randomUUID();
		String cpf = "407.302.170-27";
		Order order = new Order(uuid, cpf);
		assertEquals(0, order.getTotal());
	}

	@Test
	public void shouldCreateOrderWith3Items() {
		UUID uuid = UUID.randomUUID();
		String cpf = "407.302.170-27";
		Order order = new Order(uuid, cpf);
		order.addItem(new Product(1, "A", 1000, 100, 30, 10, 3, "BRL"), 1);
		order.addItem(new Product(2, "B", 5000, 50, 50, 50, 22, "BRL"), 1);
		order.addItem(new Product(3, "C", 30, 10, 10, 10, 0.9, "BRL"), 3);
		assertEquals(6090, order.getTotal());
	}

	@Test
	public void shouldNotAddDuplicateItem() {
		UUID uuid = UUID.randomUUID();
		String cpf = "407.302.170-27";
		Order order = new Order(uuid, cpf);
		order.addItem(new Product(1, "A", 1000, 100, 30, 10, 3, "BRL"), 1);
		assertThrows(IllegalArgumentException.class, () ->
				order.addItem(new Product(1, "A", 1000, 100, 30, 10, 3, "BRL"), 1));
	}

	@Test
	public void shouldNotAddItemWithQuantityLessThanOrEqualTo0() {
		UUID uuid = UUID.randomUUID();
		String cpf = "407.302.170-27";
		Order order = new Order(uuid, cpf);

		assertThrows(IllegalArgumentException.class, () -> {
			Product product = new Product(1, "A", 1000, 100, 30, 10, 3, "BRL");
			int quantity = -1;
			order.addItem(product, quantity);
		});
	}

	@Test
	public void shouldCreateOrderWith3ItemsIncludingOneInDollar() {
		UUID uuid = UUID.randomUUID();
		String cpf = "407.302.170-27";
		CurrencyTable currencyTable = new CurrencyTable();
		currencyTable.addCurrency("USD", 3.0);
		Order order = new Order(uuid, cpf, currencyTable);
		order.addItem(new Product(1, "A", 1000, 100, 30, 10, 3, "BRL"), 1);
		order.addItem(new Product(2, "B", 5000, 50, 50, 50, 22, "USD"), 1);
		order.addItem(new Product(3, "C", 30, 10, 10, 10, 0.9, "BRL"), 3);

		double totalInBRL = order.getTotalInBRL();

		assertEquals(16090.0, totalInBRL);
	}


	@Test
	public void shouldCreateOrderAndGenerateCode() throws ParseException {
		String uuid = String.valueOf(UUID.randomUUID());
		String cpf = "407.302.170-27";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = dateFormat.parse("2023-10-01T10:00:00");
		Order order = new Order(uuid, cpf, new CurrencyTable(), 1, date);
		assertEquals("202300000001", order.getCode());
	}
}
