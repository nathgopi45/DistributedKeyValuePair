package com.indix.distributed.keyValue.common.exception;

import java.util.List;

import com.indix.distributed.common.model.ErrorInfo;

public class DataValidationException extends RuntimeException {

	private static final long serialVersionUID = 6232221636406660878L;
	
	private List<ErrorInfo> errors;

	public DataValidationException(final String message) {
		super(message);
	}

	public DataValidationException(final String message, List<ErrorInfo> errors) {
		super(message);
		this.errors=errors;
	}

	public List<ErrorInfo> getErrors() {
		return errors;
	}
	
	
}
