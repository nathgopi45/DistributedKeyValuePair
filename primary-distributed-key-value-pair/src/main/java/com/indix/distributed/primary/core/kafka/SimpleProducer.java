package com.indix.distributed.primary.core.kafka;

import java.util.Properties;

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
import com.indix.distributed.primary.core.dao.PrimaryKeyValueDAO;
import com.indix.distributed.primary.rest.exception.InvalidKeyValueException;

//Create java class named �SimpleProducer�

@Component
public class SimpleProducer {

	@Autowired
	private PrimaryKeyValueDAO primaryKeyValueDAO;
	
	
	static Properties props = new Properties();
	static String topicName = "key-value-pair-topic";
	static {


		//Assign topicName to string variable


		// create instance for properties to access producer configs   


		//Assign localhost id
		props.put("bootstrap.servers", "localhost:9092");

		//Set acknowledgements for producer requests.      
		props.put("acks", "all");

		//If the request fails, the producer can automatically retry,
		props.put("retries", 0);

		//Specify buffer size in config
		props.put("batch.size", 16384);

		//Reduce the no of requests less than 0   
		props.put("linger.ms", 1);

		//The buffer.memory controls the total amount of memory available to the producer for buffering.   
		props.put("buffer.memory", 33554432);

		props.put("key.serializer", 
				StringSerializer.class.getName());


		Thread.currentThread().setContextClassLoader(null);


		props.put("value.serializer", 
				StringSerializer.class.getName());
	}

	/*public static void main(String[] args) throws Exception{
		Document document = new Document();
		publishEventStore(document);
	}*/

	public void publishEventStore(DistributingEvent event) {
		Producer<String, String> producer = new KafkaProducer
				<String, String>(props);
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topicName, 
				event.getEvent(), event.getEvent());
		
		KeyValuePair keyValuePair = new KeyValuePair();
		keyValuePair.setKey(producerRecord.key());
		keyValuePair.setValue(producerRecord.value());
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