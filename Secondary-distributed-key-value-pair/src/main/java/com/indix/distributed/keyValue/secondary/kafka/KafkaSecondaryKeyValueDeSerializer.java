package com.indix.distributed.keyValue.secondary.kafka;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indix.distributed.common.model.KeyValuePair;
import com.indix.distributed.common.model.events.DistributingEvent;

public class KafkaSecondaryKeyValueDeSerializer implements Deserializer<KeyValuePair> {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSecondaryKeyValueDeSerializer.class);

  private ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

  @Override
  public KeyValuePair deserialize(String key, byte[] data) {
    KeyValuePair events = null; 
    try {
      LOGGER.info("Event: {}", new String(data));
      events= mapper.readerFor(KeyValuePair.class).readValue(data);;
      
    } catch (Exception e) {
      LOGGER.error("Error while deserializing event with KafkaKey {}. Exception: {}", key ,e);
    }
    return events;
  }

  @Override
  public void close() {
    // nothing to do
  }

  @Override
  public void configure(Map<String, ?> configMap, boolean b) {}

}
