package com.indix.distributed.keyValue.secondary.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.indix.distributed.common.model.KeyValuePair;
import com.indix.distributed.keyValue.common.exception.EventHandlerException;
import com.indix.distributed.keyValue.secondary.service.SecondaryKeyValueService;

@Component
public class SecondaryKeyValueConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecondaryKeyValueConsumer.class);

	@Autowired
	private SecondaryKeyValueService secondaryKeyValueService;

	@KafkaListener(group = "key-value-pair-group", id = "keyValuePairListener", topics = {
			"key-value-pair-topic" }, containerFactory = "keyValuePairListenerContainerFactory")
	public void onMessage(ConsumerRecord<String, KeyValuePair> record) {
		String index = record.key(); // Kafka index key to store the messages.
		try {
				handleEvent(record.value());
			LOGGER.info("KEY_VALUE_PROCESSOR_EVENT_PROCESSED||{}||{}||{}||{}", index, System.currentTimeMillis(),
					record.value().getKey(), record.value().getValue());
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
