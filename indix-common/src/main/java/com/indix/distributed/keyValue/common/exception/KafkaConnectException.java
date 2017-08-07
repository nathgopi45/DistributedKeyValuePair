package com.indix.distributed.keyValue.common.exception;

public class KafkaConnectException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	
	public KafkaConnectException() {
		    super();
	}
	 
	public KafkaConnectException(String message){
		super(message);
	}

}
