/**
 * This class represents an employee. Each employee has a last name and a salary.
 * TODO: implement the class: fields, constructors, methods. See the individual TODOs below.
 */


public class Employee {
	private final String lastName;
	private double annualSalary;

	public Employee(String lastName, double annualSalary){
		this.lastName = checkNull(lastName);
		this.annualSalary = checkMaxRange(annualSalary);
	}
	

	public String getLastName(){
		return lastName;
	}

	public double getAnnualSalary(){
		return annualSalary;
	}
	public void setAnnualSalary(double annualSalary){
		this.annualSalary = checkMaxRange(annualSalary);
	}

	private String checkNull(String text){
		if (text == null){
			throw new NullPointerException("Please enter a last name!");
		}
		return text;
	}

	private double checkMaxRange(double annualSalary){
		if (annualSalary >= 0.00 && annualSalary <= 1_000_000.00){
			return annualSalary;
		}
		throw new IllegalArgumentException("The annual salary should be between 0 and 1,000,000!");
	}

    /*
     * TODO: Each employee has a field called 'lastName' to store the last name of each employee object.
     * This field can only be set in the constructor and not be changed later.
     */


    /**
     * TODO: Each employee has a field called 'annualSalary' to store the salary of each employee object.
     * The salary should be changeable later and must be able to store numbers from 0.00 to 1'000'000.00 with double precision.
     */


    /**
     * TODO: The class must provide a constructor that accepts the last name and the salary of an employee
     * and stores it in the right fields.
     */


    /**
     * TODO: The class must provide a method called 'getLastName' that returns the last name of the employee.
     */


    /**
     * TODO: There are more methods needed for this class to work and meet the clean code rules.
     */


}
