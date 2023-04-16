import org.example.ConversorDeNumeroRomano;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConversorDeNumeroRomanoTest {
    @Test
    public void deveEntenderOSimboloI() {
        ConversorDeNumeroRomano romano = new ConversorDeNumeroRomano();
        int numero = romano.converte("I");
        assertEquals(1, numero);
    }
    @Test
    public void deveEntenderDoisSimbolosComoII() {
        ConversorDeNumeroRomano romano = new ConversorDeNumeroRomano();
        int numero = romano.converte("II");
        assertEquals(2, numero);
    }
    @Test
    public void deveEntenderNumerosComoIX() {
        ConversorDeNumeroRomano romano = new ConversorDeNumeroRomano();
        int numero = romano.converte("IX");
        assertEquals(9, numero);
    }
    @Test
    public void deveEntenderNumerosComplexosComoXXIV() {
        ConversorDeNumeroRomano romano = new ConversorDeNumeroRomano();
        int numero = romano.converte("XXIV");
        assertEquals(24, numero);
    }
    @Test
    public void deveEntenderQuandoNaoEhRomano() {
        ConversorDeNumeroRomano romano = new ConversorDeNumeroRomano();

        String[] numerosRomanos = {"VV", "LL", "DD", "c", "i", "Cachorrinho", "IIII", "abc", "123", "12V", "X-L", "I I I", "", null};

        for (String numeroRomano : numerosRomanos) {
            boolean condicaoFalsa = romano.isRomano(numeroRomano);
            assertFalse(condicaoFalsa);
        }
    }
    @Test
    public void deveEntenderQuandoEhRomano() {
        ConversorDeNumeroRomano romano = new ConversorDeNumeroRomano();

        String[] numerosRomanos = {"IV", "IX", "XVII", "XXIX", "XXXIV", "XLV", "LX", "LXXIII", "XC", "CXXV", "CLIX", "CDXI", "DCCCXVIII", "CMXCIX"};

        for (String numeroRomano : numerosRomanos) {
            boolean condicaoVerdadeira = romano.isRomano(numeroRomano);
            assertTrue(condicaoVerdadeira);
        }
    }
}
