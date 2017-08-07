package com.indix.distributed.primary.core.kafka;


public class DistributedEventRouter {/*

	private static final Logger LOGGER = LoggerFactory.getLogger(DistributedEventRouter.class);
	private KafkaTemplate<String, String> kafkaTemplate;	
	
	private String topic;	
	
	public DistributedEventRouter(KafkaTemplate<String, String> kafkaTemplate,String topic) {
		this.topic=topic;
		this.kafkaTemplate=kafkaTemplate;
	}

	
	public String route(  DistributingEvent event) {
		LOGGER.debug("Publishing events to Kafka. event: {}", event);
		String key = createKey();
		publish(key, event);
		LOGGER.info("Vehicle event received and forwarded to Vehicle event handler,key: {}, event: {}", key, event);
		return key;
	}
	
	private String createKey() {
		return new StringBuilder()//
				.append(currentTimeMillis())//
				.append("-")//
				.append(new Random().nextInt(10000))//
				.toString();
	}
	
	public void publish(final String eventKey, final DistributingEvent event)  {
		kafkaTemplate.send(topic, eventKey, event.getEvent());
	}
	

*/}
