package ch02.ex10;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Vehicle {
	public long id;
	public double speed;
	public double direction;
	public String owner;
	
	public static long nextId = 0;

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

	@Override
	public String toString() {
		return "id=" + this.id + ", speed=" + this.speed + ", direction=" + this.direction + ", owner=" + this.owner;
	}

	public static void main(String[] args) {
		Vehicle toyota = new Vehicle();
		toyota.speed = 100;
		toyota.direction = 30;
		toyota.owner = "Tom";

		Vehicle benz = new Vehicle("Bob");
		benz.speed = 165.5;
		benz.direction = 200.5;

		Vehicle ferrari = new Vehicle();
		ferrari.speed = 180.4;
		ferrari.direction = 60;
		ferrari.owner = "Molly";

		System.out.println(ToStringBuilder.reflectionToString(toyota, ToStringStyle.SHORT_PREFIX_STYLE));
		System.out.println(ToStringBuilder.reflectionToString(benz, ToStringStyle.SHORT_PREFIX_STYLE));
		System.out.println(ToStringBuilder.reflectionToString(ferrari, ToStringStyle.SHORT_PREFIX_STYLE));
	}
}
