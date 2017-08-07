package com.indix.distributed.common.kafka;


import org.springframework.kafka.core.KafkaTemplate;

import com.indix.distributed.common.model.Event;

public abstract class KafkaProducer<E extends Event> implements Producer<E> {

	protected abstract String getEventTopic();

	protected abstract KafkaTemplate<String, E> getTemplate();

	public void publish(final String eventKey, final E event)  {
		getTemplate().send(getEventTopic(), eventKey, event);
	}
	

}
