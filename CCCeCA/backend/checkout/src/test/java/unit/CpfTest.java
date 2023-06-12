package unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.checkout.domain.entity.Cpf;
import org.junit.jupiter.api.Test;

public class CpfTest {
	@Test
	public void shouldTestValidCpf() {
		String[] validCpfs = {
				"407.302.170-27",
				"684.053.160-00",
				"746.971.314-01"
		};

		for (String cpfValue : validCpfs) {
			Cpf cpf = new Cpf(cpfValue);
			assertEquals(cpfValue, cpf.getValue());
		}
	}

	@Test
	public void shouldTestInvalidCpf() {
		String[] invalidCpfs = {
				"406.302.170-27",
				"406302170",
				"406302170123456789",
				"406302170123456"
		};

		for (String cpfValue : invalidCpfs) {
			assertThrows(IllegalArgumentException.class, () -> new Cpf(cpfValue));
		}
	}

	@Test
	public void shouldTestInvalidCpfWithAllDigitsEqual() {
		String[] invalidCpfs = {
				"111.111.111-11",
				"222.222.222-22"
		};

		for (String cpfValue : invalidCpfs) {
			assertThrows(IllegalArgumentException.class, () -> new Cpf(cpfValue));
		}
	}
}
