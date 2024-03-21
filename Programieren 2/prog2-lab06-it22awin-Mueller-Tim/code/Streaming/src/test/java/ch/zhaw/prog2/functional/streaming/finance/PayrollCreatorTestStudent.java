package ch.zhaw.prog2.functional.streaming.finance;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * This test class is for all test methods written by students for easier review by lecturers.
 * In a real application these test would be in the class PayrollCreatorTest.
 *
 * ✅  This class should be worked on by students.
 */
public class PayrollCreatorTestStudent {

    @Test
    void testPayrollAmountByCurrency() {
        // Mock the payroll
        Payroll payroll = Mockito.mock(Payroll.class);

        // Create a list of payments with different currencies
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment().setCurrencyAmount(new CurrencyAmount(100, Currency.getInstance("USD"))));
        payments.add(new Payment().setCurrencyAmount(new CurrencyAmount(200, Currency.getInstance("EUR"))));
        payments.add(new Payment().setCurrencyAmount(new CurrencyAmount(150, Currency.getInstance("USD"))));
        payments.add(new Payment().setCurrencyAmount(new CurrencyAmount(300, Currency.getInstance("CHF"))));
        payments.add(new Payment().setCurrencyAmount(new CurrencyAmount(250, Currency.getInstance("EUR"))));

        // Return the list of payments when getPaymentList() is called
        when(payroll.getPaymentList()).thenReturn(payments);

        // Create the expected result
        List<CurrencyAmount> expected = new ArrayList<>();
        expected.add(new CurrencyAmount(300, Currency.getInstance("CHF")));
        expected.add(new CurrencyAmount(450, Currency.getInstance("EUR")));
        expected.add(new CurrencyAmount(250, Currency.getInstance("USD")));

        // Create the PayrollCreator instance
        PayrollCreator payrollCreator = new PayrollCreator(null);

        // Call the method to be tested
        List<CurrencyAmount> result = payrollCreator.payrollAmountByCurrency(payroll);

        // Assert the result
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getAmount(), result.get(i).getAmount());
            assertEquals(expected.get(i).getCurrency(), result.get(i).getCurrency());
        }
    }
}
