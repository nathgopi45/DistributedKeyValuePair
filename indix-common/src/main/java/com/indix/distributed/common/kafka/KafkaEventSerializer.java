package com.indix.distributed.common.kafka;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indix.distributed.common.model.Event;

public class KafkaEventSerializer<T extends Event> implements Serializer<T> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventSerializer.class);
	
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public byte[] serialize(String key, T event) {
		try {
			return mapper.writeValueAsBytes(event);
		} catch (Exception e) {
			LOGGER.error("Error while serializing event. Exception: {}",e);
		}
		return "".getBytes();
	}	

	@Override
	public void close() {
		mapper=null;		
	}

	@Override
	public void configure(Map<String, ?> configMap, boolean b) {}

}
