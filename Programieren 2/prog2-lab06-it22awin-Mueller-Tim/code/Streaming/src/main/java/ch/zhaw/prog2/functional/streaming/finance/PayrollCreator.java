package ch.zhaw.prog2.functional.streaming.finance;

import ch.zhaw.prog2.functional.streaming.Company;
import ch.zhaw.prog2.functional.streaming.humanresource.Employee;

import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This Class creates a Payroll (Lohabrechnung) for a whole Company
 * and supplies some Utility Methods for a Payroll.
 * ✅  This class should be worked on by students.
 */
public class PayrollCreator {
    private final Company company;

    /**
     * Opens a Payroll for a company.
     * @param company
     */
    public PayrollCreator(Company company) {
        this.company = company;
    }

    /*
     * Aufgabe d) - Test dazu exisitert in PayrollCreatorTest
     */
    public Payroll getPayrollForAll() {
        Payroll payroll = new Payroll();
        payroll.addPayments(company.getPayments(Employee::isWorkingForCompany));
        return payroll;
    }

    /*
     * Aufgabe e) - Test dazu existiert in PayrollCreatorTest
     */
    public static int payrollValueCHF(Payroll payroll) {
        CurrencyAmount oldAmount = new CurrencyAmount(payroll.getPaymentList().stream().mapToInt(payment -> payment.getCurrencyAmount().getAmount()).sum());
        return CurrencyChange.getInNewCurrency(oldAmount, CurrencyAmount.CHF).getAmount();
    }

    /*
     * Aufgabe f) - schreiben Sie einen eigenen Test in PayrollCreatorTestStudent
     * @return a List of total amounts in this currency for each currency in the payroll
     */
    public static List<CurrencyAmount> payrollAmountByCurrency(Payroll payroll) {
      return payroll.getPaymentList().stream().map(Payment::getCurrencyAmount).collect(Collectors.groupingBy(CurrencyAmount::getCurrency)).entrySet().stream().map(entry -> new CurrencyAmount(entry.getValue().stream().mapToInt(CurrencyAmount::getAmount).sum(), entry.getKey())).collect(Collectors.toList());
    }


}
