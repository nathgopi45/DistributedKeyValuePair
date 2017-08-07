package com.indix.distributed.primary.core.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.indix.distributed.common.model.KeyValuePair;
import com.indix.distributed.keyValue.common.exception.InvalidKeyValueException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Component
public class PrimaryKeyValueDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrimaryKeyValueDAO.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(final KeyValuePair keyValuePair) throws InvalidKeyValueException {
		if (keyValuePair != null) {			
			mongoTemplate.save(keyValuePair);
			LOGGER.info("Event stored successfully. document:{}", keyValuePair);
		} else {
			throw new InvalidKeyValueException("invalid Key Value Exception");
		}
	}  

	public KeyValuePair upsert(final KeyValuePair keyValuePair) throws InvalidKeyValueException {
		Query query = new Query(Criteria.where("_id").is(keyValuePair.getKey()));
		KeyValuePair existingKeyValuePair = mongoTemplate.findOne(query, KeyValuePair.class);
		if (existingKeyValuePair != null) {
			DBObject dbDoc = new BasicDBObject();
			existingKeyValuePair.setValue(keyValuePair.getValue());
			mongoTemplate.getConverter().write(existingKeyValuePair, dbDoc);
			Update update = fromDBObjectExcludeNullFields(dbDoc);
			mongoTemplate.upsert(query, update, KeyValuePair.class);
			LOGGER.info("TripDetail {} updated successfully", existingKeyValuePair);
		} else {
			save(keyValuePair);
			LOGGER.info("LogisticUnit {} inserted successfully", keyValuePair);
		}
		return keyValuePair;
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
