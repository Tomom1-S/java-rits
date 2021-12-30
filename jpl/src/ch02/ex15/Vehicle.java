package ch02.ex15;

public class Vehicle {
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

	// 柴田さん：value に制限を設ける、など setSpeed に機能を追加してもよい
	// 負の値が与えられたときの処理を追加してもよい
	public void changeSpeed(double value) {
		this.speed = value;
	}

	public void stop() {
		changeSpeed(0);
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
