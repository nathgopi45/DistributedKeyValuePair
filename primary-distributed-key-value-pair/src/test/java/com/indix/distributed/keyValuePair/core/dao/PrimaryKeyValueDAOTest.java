package com.indix.distributed.keyValuePair.core.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import com.indix.distributed.common.model.KeyValuePair;
import com.indix.distributed.keyValue.common.exception.InvalidKeyValueException;
import com.indix.distributed.primary.core.dao.PrimaryKeyValueDAO;


@RunWith(MockitoJUnitRunner.class)
public class PrimaryKeyValueDAOTest {
	@InjectMocks
	PrimaryKeyValueDAO eventStoreDAO;

	@Mock
	MongoTemplate mongoTemplate;

	@Mock
	MongoConverter converter;


	@Before
	public void setUp() throws Exception {
		KeyValuePair keyValuePair = getStubbedKeyValuePair();
		Mockito.when(mongoTemplate.findById(Mockito.anyString(), Mockito.any())).thenReturn(keyValuePair);
		Mockito.doNothing().when(mongoTemplate).save(Mockito.any(KeyValuePair.class));
	}


	private KeyValuePair getStubbedKeyValuePair() {
		KeyValuePair keyValuePair = new KeyValuePair();
		keyValuePair.setKey("Key");
		keyValuePair.setValue("Value");
		return keyValuePair;
	}


	@Test	
	public void testOnSave() {
		try{
			eventStoreDAO.save(getStubbedKeyValuePair());
		} catch(InvalidKeyValueException e){
			e.getCause();
		}
	}

}
