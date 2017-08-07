package com.indix.distributed.primary.rest.controller;

import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.indix.distributed.common.model.ErrorInfo;
import com.indix.distributed.common.model.events.DistributingEvent;
import com.indix.distributed.primary.core.exception.KafkaConnectException;
import com.indix.distributed.primary.core.service.impl.EventStoreServiceImpl;

@RestController
public class EventController  {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);
	private final List<String> headerKeysToFilter = asList("Origin", "Accept", "Connection", "User-Agent", "Host",
			"Accept-Encoding", "Authorization", "Cache-Control", "Postman-Token", "Accept-Language", "Content-Length","Accept-charset");
	@Autowired
	@Qualifier("eventStoreService")
	private EventStoreServiceImpl eventStoreService;

	@RequestMapping(value = "v1/distributing/events", method = POST)
	@ResponseStatus(value = ACCEPTED)
	public void createGenericEvent(@RequestBody String event, @RequestHeader Map<String, String> headers)
			throws IOException {
		LOGGER.info("Event received. Headers: {} Event:{}", headers, event);
		long startTime = currentTimeMillis();
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(event);
			
			
			LOGGER.info("Event processed. Key: {},Value:{}", json.getAsString("key"),
					json.getAsString("value"));
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
				 process(convertToEvent(event, headers));
	}

	protected DistributingEvent process(DistributingEvent event) {
		return eventStoreService.process(event);
	}

	@ExceptionHandler(KafkaConnectException.class)
	public void kafkaIsDownHandler(HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ErrorInfo errorResponse = new ErrorInfo();
		errorResponse.setMessage("Kafka Event Store is Timed Out");
		errorResponse.setCode("500");
		returnErrorResponse(httpServletResponse, errorResponse);
	}

	private static void returnErrorResponse(HttpServletResponse httpServletResponse, ErrorInfo errorResponse)
			throws IOException {
		httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		httpServletResponse.setHeader("Content-type", "application/json");
		httpServletResponse.getOutputStream().write(constructResponse(500, errorResponse.getMessage()));
		httpServletResponse.getOutputStream().close();

	}

	private static byte[] constructResponse(final int httpCode, final String errorMessage) {
		StringBuilder responseBuilder = new StringBuilder();
		return responseBuilder.append("{\"status\":\"")
				.append(httpCode)//
				.append("\",\"message\":\"")//
				.append(errorMessage)//
				.append("\",\"moreInfo\":\"\"}")//
				.toString()//
				.getBytes();
	}

	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void messageNotReadableHandler(HttpMessageNotReadableException e) {
		LOGGER.error("HttpMessageNotReadableException, while processing event, {}", e);
	}



	private DistributingEvent convertToEvent(String event, final Map<String, String> headers) {
		DistributingEvent distributingEvent = new DistributingEvent();		
		distributingEvent.setCreatedOn(new Date(currentTimeMillis()));
		distributingEvent.setEvent(event);
		distributingEvent.setHeaders(filterHeaders(headers));
		return distributingEvent;
	}

	private Map<String, String> filterHeaders(Map<String, String> headers) {
		return headers.keySet()//
				.stream()//
				.filter(key -> !headerKeysToFilter.contains(key))//
				.collect(toMap(String::toLowerCase, key -> headers.get(key)));
	}

	private long calculateTotalTimeTaken(long startTime) {
		return currentTimeMillis() - startTime;
	}

}
