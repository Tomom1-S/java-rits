package models;

import lombok.Value;

@Value
public class AttractionFacility implements Facility {
    String name;
    String status;
}
