package pizzashop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    public void testConstructorAndGetters() {
        Payment payment = new Payment(3, PaymentType.Cash, 25.50);

        assertEquals(3, payment.getTableNumber());
        assertEquals(PaymentType.Cash, payment.getType());
        assertEquals(25.50, payment.getAmount());
    }

    @Test
    public void testSetters() {
        Payment payment = new Payment(1, PaymentType.Card, 10.0);

        payment.setTableNumber(5);
        payment.setType(PaymentType.Cash);
        payment.setAmount(19.99);

        assertEquals(5, payment.getTableNumber());
        assertEquals(PaymentType.Cash, payment.getType());
        assertEquals(19.99, payment.getAmount());
    }

    @Test
    public void testToString() {
        Payment payment = new Payment(2, PaymentType.Cash, 30.0);
        String expected = "2,Cash,30.0";
        assertEquals(expected, payment.toString());
    }
}
