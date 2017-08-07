package com.indix.distributed.keyValue.secondary.kafka;


import org.springframework.kafka.core.KafkaTemplate;

public abstract class KafkaProducer<E extends Event> implements Producer<E> {

	protected abstract String getEventTopic();

	protected abstract KafkaTemplate<String, E> getTemplate();

	public void publish(final String eventKey, final E event)  {
		getTemplate().send(getEventTopic(), eventKey, event);
	}
	

}
