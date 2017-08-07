package com.indix.distributed.common.kafka;

import static java.lang.System.currentTimeMillis;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Random;

import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;

import com.indix.distributed.common.model.Event;


public class GenericEventProducer<E extends Event> extends KafkaProducer<E> {

	private static final Logger LOGGER = getLogger(GenericEventProducer.class);

	private KafkaTemplate<String, E> kafkaTemplate;	
	private String topic;	
	
	public GenericEventProducer(KafkaTemplate<String, E> kafkaTemplate,String topic) {
		this.topic=topic;
		this.kafkaTemplate=kafkaTemplate;
	}

	@Override
	public String publish(E event) {
		LOGGER.debug("Publishing events to Kafka. event: {}", event);
		String key = createKey();
		publish(key, event);
		return key;
	}

	private String createKey() {
		return new StringBuilder()//
				.append(currentTimeMillis())//
				.append("-")//
				.append(new Random().nextInt(10000))//
				.toString();
	}

	@Override
	protected String getEventTopic() {
		return this.topic;
	}

	@Override
	protected KafkaTemplate<String, E> getTemplate() {
		return kafkaTemplate;
	}

}
