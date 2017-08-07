package com.indix.distributed.common.model;

public enum EventCategory {
  
  VEHICLE("Vehicle"),
  LOGISTICUNIT("Logisticunit"),
  GEOLOCATION("GeoLocation");
  
  private String value;
  
  private EventCategory(String value) {
    this.value = value;
  }
  
  public String value() {
	return value;
  }
  
}
