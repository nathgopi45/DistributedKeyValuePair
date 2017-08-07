package com.indix.distributed.keyValue.secondary.kafka;

public interface Event {	
	String getEventSource();
	void setEventSource(String eventSource);
}
