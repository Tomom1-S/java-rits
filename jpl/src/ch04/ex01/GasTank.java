package ch04.ex01;

public class GasTank implements EnergySource {
    private double remainingAmount;

    public GasTank() {}

    public GasTank(double remainingAmount) {
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
    public boolean empty() {
        return (remainingAmount <= 0);
    }
}
