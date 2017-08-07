package com.indix.distributed.keyValue.secondary.exception;

import com.indix.distributed.keyValue.secondary.kafka.Event;

public class EventHandlerException extends Exception {

	private static final long serialVersionUID = 1779384634976606101L;

	private final String errorCode;
	
	private final Event event;

	public EventHandlerException(final Event event) {
		super();
		this.event=event;
		this.errorCode=null;
	}
	
	public EventHandlerException(final Event event,final String message) {
		super(message);
		this.event=event;
		this.errorCode=null;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Event getEvent() {
		return event;
	}


}
