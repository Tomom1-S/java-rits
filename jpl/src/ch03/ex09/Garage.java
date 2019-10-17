package ch03.ex09;

import ch03.ex08.Vehicle;
import org.apache.commons.lang3.ArrayUtils;

public class Garage implements Cloneable {
    private long id;
    private Vehicle[] vehicles;

    private static long nextId = 0;

    public Garage() {
        this.vehicles = new Vehicle[] {};
    }

    public Garage(int size) {
        this.vehicles = new Vehicle[size];
    }

    public Garage(Vehicle vehicle, Vehicle... rest) {
        this.vehicles = new Vehicle[1 + rest.length];
        this.vehicles[0] = vehicle;

        if (rest.length == 0) {
            return;
        }
        for (int i = 0; i < rest.length; i++) {
            vehicles[i + 1] = rest[i];
        }
    }

    public Vehicle[] getGarage() {
        return vehicles;
    }

    public Vehicle getVehicle(int index) {
        return vehicles[index];
    }

    public Garage clone() {
        Garage garage = new Garage();
        try {
            garage = (Garage) super.clone();
            garage.vehicles = vehicles.clone();
            return garage;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }

    public void setVehicle(int index, Vehicle vehicle) {
        vehicles[index] = vehicle;
    }

    public int findVehicle(Vehicle vehicle) {
        return ArrayUtils.indexOf(vehicles, vehicle);
    }

    public static boolean main(String[] args) {
        Vehicle vehicle0 = new Vehicle("Tom");
        vehicle0.setDirection(20);
        vehicle0.setSpeed(100);

        Vehicle vehicle1 = new Vehicle("Bob");
        vehicle1.setDirection(200.5);
        vehicle1.setSpeed(165.5);

        Vehicle vehicle2 = new Vehicle("Molly");
        vehicle2.setDirection(60);
        vehicle2.setSpeed(180.4);

        Garage org = new Garage(vehicle0, new Vehicle[] {vehicle1, vehicle2});
        Garage copy = org.clone();

        if (copy != org && copy.getClass() == org.getClass()) {
            return true;
        }
        return false;
    }
}
