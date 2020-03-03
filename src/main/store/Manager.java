package store;

public class Manager extends Employee {

	public Manager(String firstName, String lastName, float fixedSalary) {
		super(firstName, lastName, fixedSalary);
	}

	public float salaryAfterAdditionsAndDeductions() {
		float benefits = salaryBenefits();
		float pensionFounds = pensionFounds();
		float tax = 0;
		if (applyTax())
			tax = tax();
		return fixedSalary + benefits - pensionFounds - tax;
	}

	private float tax() {
		return fixedSalary * 5 / 100;
	}

	private boolean applyTax() {
		return fixedSalary > 3500;
	}

	private float pensionFounds() {
		return this.fixedSalary * 10 / 100;
	}

	private float salaryBenefits() {
		return this.subordinates.size() * 20;
	}
}
