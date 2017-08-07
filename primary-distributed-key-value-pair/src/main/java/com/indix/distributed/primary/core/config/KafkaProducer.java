package com.indix.distributed.primary.core.config;


import org.springframework.kafka.core.KafkaTemplate;

public abstract class KafkaProducer<T,E> implements Producer<T,E> {

	protected abstract T getEventTopic();

	protected abstract KafkaTemplate<T, E> getTemplate();

	public void publish(final T eventKey, final E event)  {
		getTemplate().send((String)getEventTopic(), eventKey, event);
	}
	

}
