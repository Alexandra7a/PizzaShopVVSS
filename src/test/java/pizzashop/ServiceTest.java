package pizzashop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceTest {

    private PaymentRepository paymentRepository;
    private MenuRepository menuRepository;
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        // Mocking the PaymentRepository dependency
        paymentRepository = mock(PaymentRepository.class);
        // Using the real MenuRepository since it’s not relevant to these tests
        menuRepository = new MenuRepository();
        // Injecting mock into the service
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @Test
    void test_addPayment_valid() {
        Payment p1 = new Payment(5, PaymentType.Cash, 55.00);
        Payment p2 = new Payment(6, PaymentType.Card, 25.00);

        // Simulate repository behavior
        List<Payment> mockPayments = new ArrayList<>();
        mockPayments.add(p1);

        when(paymentRepository.getAll()).thenReturn(mockPayments);
        doNothing().when(paymentRepository).add(any(Payment.class));

        // Call service method
        pizzaService.addPayment(p2.getTableNumber(), p2.getType(), p2.getAmount());

        // Verify interaction with mock repository
        verify(paymentRepository, times(1)).add(any(Payment.class));

        List<Payment> payments = paymentRepository.getAll();
        assertNotNull(payments);
        assertEquals(1, payments.size());

        verify(paymentRepository, times(1)).getAll();
    }

    @Test
    void test_addPayment_invalidAmount_throwsException() {
        // Use a real repository wrapped in a spy to check behavior during exception
        PaymentRepository spyRepository = spy(new PaymentRepository());
        PizzaService serviceWithSpy = new PizzaService(menuRepository, spyRepository);

        // Call with invalid amount
        assertThrows(IllegalArgumentException.class, () -> {
            serviceWithSpy.addPayment(5, PaymentType.Cash, -500);
        });

        // Ensure writeAll was never called due to exception
        verify(spyRepository, never()).writeAll();
    }
}