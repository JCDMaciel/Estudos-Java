package unit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.example.checkout.domain.entity.Coupon;
import org.junit.Test;

public class CouponTest {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	@Test
	public void shouldCreateValidCoupon() throws ParseException {
		Date expirationDate = dateFormat.parse("2023-10-01T10:00:00");
		Coupon coupon = new Coupon("VALE20", 20, expirationDate);
		assertFalse(coupon.isExpired(dateFormat.parse("2023-02-01T10:00:00")));
	}

	@Test
	public void shouldCreateInvalidCoupon() throws ParseException {
		Date expirationDate = dateFormat.parse("2023-10-01T10:00:00");
		Coupon coupon = new Coupon("VALE20", 20, expirationDate);
		assertTrue(coupon.isExpired(dateFormat.parse("2023-12-01T10:00:00")));
	}

	@Test
	public void shouldCalculateDiscount() throws ParseException {
		Date expirationDate = dateFormat.parse("2023-10-01T10:00:00");
		Coupon coupon = new Coupon("VALE20", 20, expirationDate);
		assertEquals(200, coupon.calculateDiscount(1000));
	}
}
