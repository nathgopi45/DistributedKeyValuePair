package com.indix.distributed.primary.core.kafka;

import org.springframework.stereotype.Component;

@Component
public class KafkaStoreConsumer{/*		
	
	@Autowired
	private DistributedEventRouter distributedEventRouter;
	
	@KafkaListener(group="event-store-group", id="eventStoreKafkaListener", topics={"event-stream"},	containerFactory="kafkaListenerContainerFactory")
	public void onMessage(ConsumerRecord<String, DistributingEvent> record){
		distributedEventRouter.route(record.value());		
	}

*/}
