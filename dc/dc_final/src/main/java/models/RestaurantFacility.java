package models;

import lombok.Value;

@Value
public class RestaurantFacility implements Facility {
    String name;
    String status;
}
