package com.indix.distributed.keyValue.secondary.kafka;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;

import com.indix.distributed.common.model.events.DistributingEvent;
import com.indix.distributed.keyValue.secondary.config.SecondaryKeyValueApplicationConfiguration;

@Configuration
@EnableKafka
public class KafkaSecondaryKeyValueConfig {

	private static final Logger LOGGER = getLogger(KafkaSecondaryKeyValueConfig.class);

	@Autowired
	private SecondaryKeyValueApplicationConfiguration appconfig;

	@Bean
	public <T extends Event> ProducerFactory<String, T> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public <T extends Event> KafkaTemplate<String, T> kafkaTemplate() {
		KafkaTemplate<String, T> kafkaTemplate = new KafkaTemplate<String, T>(producerFactory());

		kafkaTemplate.setProducerListener(new ProducerListener<String, T>() {
			@Override
			public boolean isInterestedInSuccess() {
				return false;
			}

			@Override
			public void onError(String topic, Integer partition, String key, T value, Exception exception) {
				LOGGER.error("Connection exception while posting to Kafka. event: {}, {}", value, exception);
			}

			@Override
			public void onSuccess(String topic, Integer partition, String key, T value, RecordMetadata recordMetadata) {
				LOGGER.info("Event published sucessfully to Kafka. event: {}", value);
			}
		});

		return kafkaTemplate;
	}

	private Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, appconfig.getKafkaBrokerAdress());
		props.put(ProducerConfig.RETRIES_CONFIG, 3);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		return props;
	}

	private Map<String, Object> consumerConfig(String groupId, Class deserializer) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, appconfig.getKafkaBrokerAdress());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer.getName());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		return props;
	}

	public ConsumerFactory<String, DistributingEvent> consumerFactory(String groupId, Class deserializer) {
		return new DefaultKafkaConsumerFactory<>(consumerConfig(groupId, deserializer));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, DistributingEvent> keyValuePairListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, DistributingEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory("key-value-pair-group", KafkaSecondaryKeyValueDeSerializer.class));
		return factory;
	}
	
}
