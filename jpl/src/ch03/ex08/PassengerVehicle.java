package ch03.ex08;

import java.sql.PseudoColumnUsage;

public class PassengerVehicle extends Vehicle implements Cloneable {
    private int seats;
    private int passengers;

    public PassengerVehicle() {
        super();
    }

    public PassengerVehicle(String owner) {
        super(owner);
    }

    public PassengerVehicle clone() {
        PassengerVehicle obj = new PassengerVehicle();
        obj = (PassengerVehicle) super.clone();
        return obj;
    }

    public int getSeats() {
        return seats;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setSeats(int seats) {
        if (seats < 0) {
            throw new IllegalArgumentException("Number of seats should be a positive value!");
        }
        if (seats < passengers) {
            throw new IllegalArgumentException("Number of seats should be more than or equal to number of passengers!");
        }
        this.seats = seats;
    }

    public void setPassengers(int passengers) {
        if (passengers < 0) {
            throw new IllegalArgumentException("Number of passengers should be a positive value!");
        }
        if (passengers > seats) {
            throw new IllegalArgumentException("Number of passengers should be less than or equal to number of seats!");
        }
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "id=" + getId() + ", speed=" + getSpeed() + ", direction=" + getDirection() + ", owner=" + getOwner()
                + ", number of seats=" + this.seats + ", number of passengers=" + this.passengers;
    }

    public static void main(String[] args) {
        PassengerVehicle pVehicle1 = new PassengerVehicle("Steven");
        pVehicle1.setDirection(40);
        pVehicle1.setSpeed(200);
        pVehicle1.setSeats(9);
        pVehicle1.setPassengers(2);
        System.out.println(pVehicle1.toString());

        PassengerVehicle pVehicle2 = new PassengerVehicle("Hanna");
        pVehicle2.setDirection(400);
        pVehicle2.setSpeed(100);
        pVehicle2.setSeats(5);
        pVehicle2.setPassengers(4);
        System.out.println(pVehicle2.toString());
    }
}
