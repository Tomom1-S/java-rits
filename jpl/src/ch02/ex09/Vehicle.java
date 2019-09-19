package ch02.ex09;

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

	public static void main(String[] args) {
		Vehicle car = new Vehicle();
		car.speed = 100;
		car.direction = 30;
		car.owner = "Cathy";

		Vehicle bicycle = new Vehicle("Bob");
		bicycle.speed = 10.6;
		bicycle.direction = 200.5;

		Vehicle motorcycle = new Vehicle();
		motorcycle.speed = 55.5;
		motorcycle.direction = 60;
		motorcycle.owner = "Molly";

		System.out.println(ToStringBuilder.reflectionToString(car, ToStringStyle.SHORT_PREFIX_STYLE));
		System.out.println(ToStringBuilder.reflectionToString(bicycle, ToStringStyle.SHORT_PREFIX_STYLE));
		System.out.println(ToStringBuilder.reflectionToString(motorcycle, ToStringStyle.SHORT_PREFIX_STYLE));
	}
}
