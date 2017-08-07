package com.indix.distributed.keyValue.secondary.dao;

import org.bson.BSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Update;

import com.indix.distributed.common.model.KeyValuePair;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@RunWith(MockitoJUnitRunner.class)
public class SecondaryKeyValueDAOTest {

	@InjectMocks
	SecondaryKeyValueDAO eventHandlerDAO;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private MongoTemplate mongoTemplate;

	@Mock
	MongoConverter converter;

	@Before
	public void setUp() throws Exception {
		KeyValuePair keyValuePair = new KeyValuePair();
		keyValuePair.setKey("Key");
		keyValuePair.setValue("Value");
		Mockito.when(mongoTemplate.findById(Mockito.anyString(), Mockito.any())).thenReturn(keyValuePair);
		Mockito.doNothing().when(mongoTemplate).save(Mockito.any(KeyValuePair.class));
	}


	private KeyValuePair getMockedKeyValue() {
		KeyValuePair keyValuePair = new KeyValuePair();
		keyValuePair.setKey("Key1");
		keyValuePair.setValue("Value1");
		return keyValuePair;
	}

	@Test
	public void testSaveKeyValuePair() {
		eventHandlerDAO.save(getMockedKeyValue());
	}

	@Test
	public void testFindValueByKey() {
		Assert.assertNotNull(eventHandlerDAO.getValue("Key"));
	}

	@Test
	public void testDBObjectToUpdateConversion() {
		Update update = eventHandlerDAO.fromDBObjectExcludeNullFields(getSampleDBObject());
		Assert.assertNotNull(update);
		BSONObject object = (BSONObject) update.getUpdateObject().get("$set");
		Assert.assertTrue(object.keySet().size() == 2);
		Assert.assertTrue(object.keySet().contains("test1"));
		Assert.assertTrue(object.keySet().contains("test3"));
		Assert.assertFalse(object.keySet().contains("test2"));
	}

	private DBObject getSampleDBObject() {
		DBObject dbObject = new BasicDBObject();
		dbObject.put("test1", "1");
		dbObject.put("test2", null);
		dbObject.put("test3", "3");
		return dbObject;
	}

}
