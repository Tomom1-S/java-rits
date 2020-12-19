package ch03.ex06;

public class GasTank extends EnergySource {
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
    boolean empty() {
        // 柴田さん
        return (remainingAmount <= 0);
    }
}
