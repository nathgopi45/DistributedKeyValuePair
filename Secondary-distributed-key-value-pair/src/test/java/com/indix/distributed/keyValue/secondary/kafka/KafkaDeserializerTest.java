package com.indix.distributed.keyValue.secondary.kafka;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indix.distributed.common.model.KeyValuePair;
import com.indix.distributed.common.model.events.DistributingEvent;

public class KafkaDeserializerTest {

  KafkaSecondaryKeyValueDeSerializer deserializer = new KafkaSecondaryKeyValueDeSerializer();

  ObjectMapper objectMapper = new ObjectMapper();

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testDeserialize() throws JsonProcessingException { 
    KeyValuePair events = deserializer.deserialize("", getByteData());
    Assert.assertEquals("testValue", events.getValue());
  }

  @Test
  public void testDeserializeAfterClosing() throws JsonProcessingException {
    deserializer.close();
    KeyValuePair events = deserializer.deserialize("", getByteData());
    deserializer.configure(null, false);
    Assert.assertNotNull(events);
  }

  public byte[] getByteData() throws JsonProcessingException {
    KeyValuePair event = new KeyValuePair();
    event.setKey("testKey");
    event.setValue("testValue");
    return objectMapper.writeValueAsBytes(event);
  }
}
