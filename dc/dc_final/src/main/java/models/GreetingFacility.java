package models;

import lombok.Value;

@Value
public class GreetingFacility implements Facility {
    String name;
    int waitingTime;
}
