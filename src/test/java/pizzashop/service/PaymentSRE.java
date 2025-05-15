package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentSRE {

    private PaymentRepository paymentRepository;
    private MenuRepository menuRepository;
    private PizzaService pizzaService;
    private Payment payment;
    @BeforeEach
    void setUp() {

        paymentRepository = new PaymentRepository();
        menuRepository = new MenuRepository();
        pizzaService =new PizzaService(menuRepository, paymentRepository);
        payment=new Payment(0,null,0);
    }

    @Test
    public void TestAddValid() {
        payment.setAmount(25.00);
        payment.setType(PaymentType.Card);
        payment.setTableNumber(6);

        assertEquals(0,pizzaService.getPayments().size());

        /*assertEquals(0,pizzaService.getPayments().size());
        pizzaService.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());
        assertEquals(1,pizzaService.getPayments().size());*/

    }
    @Test
    public void test_add_invalid() throws IllegalArgumentException{
        payment.setAmount(-25.00);
        payment.setType(PaymentType.Card);
        payment.setTableNumber(6);

        assertEquals(0,pizzaService.getPayments().size());
        assertThrows(IllegalArgumentException.class, () -> pizzaService.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount()));
        assertEquals(0,pizzaService.getPayments().size());
    }


}