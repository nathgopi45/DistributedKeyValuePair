package com.indix.distributed.keyValue.secondary.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indix.distributed.common.model.KeyValuePair;
import com.indix.distributed.keyValue.secondary.exception.EventHandlerException;
import com.indix.distributed.keyValue.secondary.service.SecondaryKeyValueService;

@Component
public class SecondaryKeyValueConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecondaryKeyValueConsumer.class);

	@Autowired
	private SecondaryKeyValueService secondaryKeyValueService;

	private ObjectMapper mapper = new ObjectMapper();

	@KafkaListener(group = "key-value-pair-group", id = "keyValuePairListener", topics = {
			"key-value-pair-topic" }, containerFactory = "keyValuePairListenerContainerFactory")
	public void onMessage(ConsumerRecord<String, List<KeyValuePair>> record) {
		String index = record.key(); // Kafka index key to store the messages.
		List<KeyValuePair> events = record.value();
		try {
			for (KeyValuePair event : events) {
				handleEvent(event);
			}
			LOGGER.info("KEY_VALUE_PROCESSOR_EVENT_PROCESSED||{}||{}||{}||{}", index, System.currentTimeMillis(),
					events.get(0).getKey(), events.get(0).getValue());
		} catch (EventHandlerException e) {
			LOGGER.error("Exception while processing event. Event {}, Exception {}", e.getEvent(), e);
		} catch (Exception e) {
			LOGGER.error("Exception while processing event. Exception {}", e);
		}
	}

	private void handleEvent(KeyValuePair keyValuePair) throws EventHandlerException {
			secondaryKeyValueService.processEvent(keyValuePair);
	}

}
