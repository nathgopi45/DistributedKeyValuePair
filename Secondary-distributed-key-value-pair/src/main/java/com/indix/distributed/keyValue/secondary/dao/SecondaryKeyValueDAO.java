package com.indix.distributed.keyValue.secondary.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.indix.distributed.common.model.KeyValuePair;
import com.mongodb.DBObject;

@Component
public class SecondaryKeyValueDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecondaryKeyValueDAO.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	public KeyValuePair getValue(String key) {
		return findById(key);
	}

	private KeyValuePair findById(String key) {
		KeyValuePair keyValuePair = mongoTemplate.findById(key, KeyValuePair.class);
		return keyValuePair;
	}

	public void save(KeyValuePair secondaryKeyValuePair) {
		mongoTemplate.save(secondaryKeyValuePair);
	}

	public Update fromDBObjectExcludeNullFields(DBObject object) {
		Update update = new Update();
		for (String key : object.keySet()) {
			Object value = object.get(key);
			if (value != null) {
				update.set(key, value);
			}
		}
		return update;
	}

}
