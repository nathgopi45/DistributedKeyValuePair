package com.indix.distributed.primary.core.config;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indix.distributed.common.model.events.DistributingEvent;

public class KafkaEventDeSerializer implements Deserializer<DistributingEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventDeSerializer.class);
	
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public DistributingEvent deserialize(String key, byte[] data) {
		try {
			LOGGER.info("Event: {}",new String(data));
			return mapper.readerFor(DistributingEvent.class).readValue(data);
		} catch (Exception e) {
			LOGGER.error("Error while deserializing event. Exception: {}",e);
		}
		return null; 
	}

	@Override
	public void close() {
		mapper = null;
	}

	@Override
	public void configure(Map<String, ?> configMap, boolean b) {}

}
