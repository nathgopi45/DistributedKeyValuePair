package com.indix.distributed.keyValue.secondary.controller;

import static org.springframework.http.HttpStatus.OK;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.indix.distributed.common.model.KeyValuePair;
import com.indix.distributed.keyValue.secondary.service.SecondaryKeyValueService;

@RestController
public class SecondaryKeyValueController {

	public static final Logger LOGGER = LoggerFactory.getLogger(SecondaryKeyValueController.class);

	@Autowired
	SecondaryKeyValueService service;



	@RequestMapping(value = "v1/secondaryKeyValue/key/{key}", method = RequestMethod.GET)
	@ResponseStatus(value = OK)
	public KeyValuePair secondaryKeyValue(@PathVariable(required = true) String key) {
		KeyValuePair  value = null;
		try {
			value=service.getValue(key);
		} catch (Exception e) {
			LOGGER.info("Exception received : ", e);
		}

		return value;
	}

}
