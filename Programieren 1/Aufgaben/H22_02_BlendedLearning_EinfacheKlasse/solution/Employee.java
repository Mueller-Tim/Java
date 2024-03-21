package solution;

/**
 * This class represents an employee. It has a name and a salary.
 */
public class Employee {
    private String lastName;
    private double annualSalary;
    private static double MAXIMUM_VALID_SALARY= 1_000_000.0;

    public Employee (String name, double salary) {
        this.lastName = name;
        this.annualSalary = salary;
    }

    public String getLastName() {
        return lastName;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double salary) {
        if (salary >= 0 && salary <= MAXIMUM_VALID_SALARY) {
            this.annualSalary = salary;
        }
    }
}
