package ch03.ex06;

public class Battery extends EnergySource {
    private double remainingAmount;

    public Battery() {}

    public Battery(double remainingAmount) {
        setRemainingAmount(remainingAmount);
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        if (remainingAmount < 0) {
            throw new IllegalArgumentException("Remaining amount should be more than or equal to 0!");
        }
        this.remainingAmount = remainingAmount;
    }

    @Override
    boolean empty() {
        if (remainingAmount <= 0) {
            return true;
        }
        return false;
    }
}
