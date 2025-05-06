package pizzashop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.service.PizzaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;
    private MenuRepository menuRepository;
    private PizzaService pizzaService;
    @BeforeEach
    void setUp() {
         paymentRepository = Mockito.spy(new PaymentRepository());
    }

    @Test
    void test_add_valid() {
        Payment p2 = new Payment(6, PaymentType.Card, 25.00);

        /*// Prepare a mock list to simulate repository behavior
        List<Payment> mockPayments = new ArrayList<>();
        mockPayments.add(p1);

        // comportamente pe care sa le aiba in momentul in care apeleaza repo mock
        Mockito.when(paymentRepository.getAll()).thenReturn(mockPayments);
        Mockito.doNothing().when(paymentRepository).add(Mockito.any(Payment.class));
        */

        //apel
        paymentRepository.add(p2);

        // Verifica functia add si de cate ori s-a apelat mai sus
        Mockito.verify(paymentRepository, times(1)).add(p2);

        List<Payment> payments = paymentRepository.getAll();
        assertNotNull(payments);
        assertEquals(1, payments.size());

        Mockito.verify(paymentRepository, times(1)).getAll();
    }
    @Test
    public void test_add_invalid() throws IllegalArgumentException{
        //doar spy se ocupa de exceptii
        Payment invalidPayment = new Payment(5, PaymentType.Cash, -500);

        assertThrows(IllegalArgumentException.class, () -> paymentRepository.add(invalidPayment));

        Mockito.verify(paymentRepository, never()).writeAll();
    }

}