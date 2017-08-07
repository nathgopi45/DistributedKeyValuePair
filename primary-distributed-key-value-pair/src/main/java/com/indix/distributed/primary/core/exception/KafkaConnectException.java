package com.indix.distributed.primary.core.exception;

public class KafkaConnectException extends RuntimeException {

	private static final long serialVersionUID = -2276887648968356084L;

	public KafkaConnectException() {
		    super();
	}
	 
	public KafkaConnectException(String message){
		super(message);
	}

}
