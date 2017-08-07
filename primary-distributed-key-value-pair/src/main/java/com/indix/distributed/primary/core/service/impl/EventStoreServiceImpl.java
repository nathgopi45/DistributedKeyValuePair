package com.indix.distributed.primary.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.indix.distributed.common.model.events.DistributingEvent;
import com.indix.distributed.primary.core.dao.PrimaryKeyValueDAO;
import com.indix.distributed.primary.core.kafka.SimpleProducer;

@Component("eventStoreService")
public class EventStoreServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventStoreServiceImpl.class);


	@Autowired
	private SimpleProducer simpleProducer;

	@Autowired
	private PrimaryKeyValueDAO eventStoreDao;

	public DistributingEvent process(DistributingEvent event) {
		LOGGER.debug("Event is converted to document. event;{}", event);
		simpleProducer.publishEventStore(event);
		return event;
	}

}
