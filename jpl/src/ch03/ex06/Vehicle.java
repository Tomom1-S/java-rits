package ch03.ex06;

public class Vehicle {
    public final static int TURN_LEFT = -1;
    public final static int TURN_RIGHT = 1;
    private final static int DELTA = 90;

    private long id;
    private double speed;
    private double direction;
    private String owner;

    private static long nextId = 0;

    public Battery battery = new Battery();
    public GasTank gasTank = new GasTank();

    public Vehicle() {
        this.id = nextId++;
    }

    public Vehicle(String owner) {
        this();
        this.owner = owner;
    }

    public static long getMaxId() {
        return nextId - 1;
    }

    public void changeSpeed(double value) {
        this.speed = value;
    }

    public void start() throws Exception {
    	if (battery.getRemainingAmount() > 0 && gasTank.getRemainingAmount() > 0) {
    		return;
		}
    	throw new Exception("Some energy sources are empty!");
	}

    public void stop() {
        changeSpeed(0);
    }

    public void turn(double delta) {
        setDirection(getDirection() + delta);
    }

    public void turn(int rotation) {
        setDirection(getDirection() + (rotation * DELTA));
    }

    @Override
    public String toString() {
        return "id=" + this.id + ", speed=" + this.speed + ", direction=" + this.direction + ", owner=" + this.owner;
    }

    public long getId() {
        return this.id;
    }

    public double getSpeed() {
        return this.speed;
    }

    public double getDirection() {
        return this.direction;
    }

    public String getOwner() {
        return this.owner;
    }

    public long getNextId() {
        return Vehicle.nextId;
    }

    public void setSpeed(double value) {
        this.speed = value;
    }

    public void setDirection(double value) {
        this.direction = value;
    }

    public void setOwner(String value) {
        this.owner = value;
    }

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle(args[0]);

        System.out.println(vehicle.toString());
    }
}
