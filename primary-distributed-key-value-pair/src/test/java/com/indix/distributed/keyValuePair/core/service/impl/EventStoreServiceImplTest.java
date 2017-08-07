package com.indix.distributed.keyValuePair.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.indix.distributed.common.model.events.DistributingEvent;
import com.indix.distributed.primary.core.config.Producer;
import com.indix.distributed.primary.core.dao.PrimaryKeyValueDAO;
import com.indix.distributed.primary.core.kafka.SimpleProducer;
import com.indix.distributed.primary.core.service.impl.EventStoreServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EventStoreServiceImplTest {
	@Mock
	private PrimaryKeyValueDAO eventStoreDAO;

	@Mock
	private SimpleProducer simpleProducer;

	@Mock
	private Producer<String,DistributingEvent> producer;

	@InjectMocks
	private EventStoreServiceImpl eventStoreServiceImpl;

	@Test
	public void serviceProcessShouldCallFindFunction() {
		Mockito.doNothing().when(simpleProducer).publishEventStore(Mockito.any(DistributingEvent.class));;
		eventStoreServiceImpl.process((DistributingEvent) createDistributingEvent());
	}


	private DistributingEvent createDistributingEvent() {
		DistributingEvent distributingEvent = new DistributingEvent();
		distributingEvent.setCreatedOn(new Date());
		distributingEvent.setEvent("testing kafkaProducer function");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("EventType", "keyPairEvnet");
		distributingEvent.setHeaders(headers);
		return distributingEvent;
	}

	/*
	 * private Event createEvent() { Event event=trackingEvent; return event; }
	 */

}
