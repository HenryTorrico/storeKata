package store;

public class Salesman extends Employee {

	private int commissionPorcentage;
	private float monthQuota;

	public Salesman(String firstName, String lastName, float fixedSalary, int commissionPorcentage){
    	super(firstName, lastName, fixedSalary);
        this.commissionPorcentage = commissionPorcentage;
    }

	public void setCommissionPorcentage(int commissionPorcentage) {
		this.commissionPorcentage = commissionPorcentage;
	}

	public int getCommissionPorcentage() {
		return commissionPorcentage;
	}

	public float getMonthQuota() {
		return monthQuota;
	}

	public float netSalary() {
		float benefits = itsBenefits();
		float pensionFounds = pensionFounds();
		float tax = 0;
		if (applyTax())
			tax = tax();
		return fixedSalary+benefits - pensionFounds - tax;
	}

	private float tax() {
		return fixedSalary * 5 / 100;
	}

	private boolean applyTax() {
		return fixedSalary > 3500;
	}

	private float pensionFounds() {
		return fixedSalary * 10 / 100;
	}

	private float itsBenefits() {
		return monthQuota * commissionPorcentage / 100;
	}

	public void updateMonthQuota(float addQuota) {
		monthQuota = monthQuota + addQuota;
	}
}
