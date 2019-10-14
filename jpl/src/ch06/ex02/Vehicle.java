package ch06.ex02;

public class Vehicle {

	public final static TurnDirection turnDirection = TurnDirection.TURN_LEFT;
	private final static int DELTA = 90;

	private long id;
	private double speed;
	private double direction;
	private String owner;
	
	private static long nextId = 0;

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

	public void stop() {
		changeSpeed(0);
	}

	public void turn(double delta) {
		setDirection(getDirection() + delta);
	}

	public void turn(TurnDirection rotation) {
		switch (rotation) {
			case TURN_LEFT:
				setDirection(getDirection() - DELTA);
				break;
			case TURN_RIGHT:
				setDirection(getDirection() + DELTA);
				break;
		}
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
}

enum TurnDirection {
	TURN_LEFT, TURN_RIGHT
}
