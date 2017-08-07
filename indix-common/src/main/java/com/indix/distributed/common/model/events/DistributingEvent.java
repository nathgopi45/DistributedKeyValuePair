package com.indix.distributed.common.model.events;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.indix.distributed.common.model.Event;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistributingEvent {

  private Date createdOn;
  private String event;
  private Map<String, String> headers;
  
  @JsonIgnoreProperties
  private static String EVENT_SOURCE = "event-source";
  @JsonIgnoreProperties
  private static String EVENT_CATEGORY = "event-category";
  
  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("TrackingEvent [createdOn=");
    builder.append(createdOn != null ? createdOn.getTime() : null);
    builder.append(", event=");
    builder.append(event);
    builder.append(", headers=");
    builder.append(headers);
    builder.append("]");
    return builder.toString();
  }

  
  public String getEventCategory() {
    return headers == null ? null : headers.get(EVENT_CATEGORY);
  }
  
  public void setEventCategory(String eventCategory) {
	if (headers == null) {
	  headers = new HashMap<String, String>();
	}
	headers.put(EVENT_CATEGORY, eventCategory);
  }
}
