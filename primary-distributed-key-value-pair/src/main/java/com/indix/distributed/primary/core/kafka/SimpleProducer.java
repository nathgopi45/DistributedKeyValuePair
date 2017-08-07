package com.indix.distributed.primary.core.kafka;

import java.util.Properties;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;
//import simple producer packages
import org.apache.kafka.clients.producer.Producer;
//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.indix.distributed.common.model.KeyValuePair;
import com.indix.distributed.common.model.events.DistributingEvent;
import com.indix.distributed.keyValue.common.exception.InvalidKeyValueException;
import com.indix.distributed.primary.core.dao.PrimaryKeyValueDAO;


@Component
public class SimpleProducer {

	@Autowired
	private PrimaryKeyValueDAO primaryKeyValueDAO;


	static Properties props = new Properties();
	static String topicName = "key-value-pair-topic";
	static {


		props.put("bootstrap.servers", "localhost:9092");

		props.put("acks", "all");

		props.put("retries", 0);

		props.put("batch.size", 16384);

		props.put("linger.ms", 1);

		props.put("buffer.memory", 33554432);

		props.put("key.serializer", 
				StringSerializer.class.getName());


		Thread.currentThread().setContextClassLoader(null);


		props.put("value.serializer", 
				StringSerializer.class.getName());
	}

	public void publishEventStore(DistributingEvent event) throws ParseException {
		Producer<String, String> producer = new KafkaProducer
				<String, String>(props);
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topicName, 
				event.getEvent(), event.getEvent());
		@SuppressWarnings("deprecation")
		JSONParser parser = new JSONParser(); 
		JSONObject json = (JSONObject) parser.parse(event.getEvent());
		KeyValuePair keyValuePair = new KeyValuePair();
		keyValuePair.setKey(json.getAsString("key"));
		keyValuePair.setValue(json.getAsString("value"));
		try {
			primaryKeyValueDAO.save(keyValuePair);
		} catch (InvalidKeyValueException e) {
			e.printStackTrace();
		}
		producer.send(producerRecord);

		System.out.println("Message sent successfully="+producerRecord);

		producer.close();
	}
}