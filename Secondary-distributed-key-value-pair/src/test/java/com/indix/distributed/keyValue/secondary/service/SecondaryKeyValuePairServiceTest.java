package com.indix.distributed.keyValue.secondary.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.indix.distributed.common.model.KeyValuePair;
import com.indix.distributed.keyValue.secondary.dao.SecondaryKeyValueDAO;


@RunWith(MockitoJUnitRunner.class)
public class SecondaryKeyValuePairServiceTest {

	@InjectMocks
	private SecondaryKeyValueService service;

	@Mock
	private SecondaryKeyValueDAO secondaryKeyValueDAO;


	@Before
	public void setUp() throws Exception {
		KeyValuePair keyValuePair = new KeyValuePair();
		keyValuePair.setKey("Key");
		keyValuePair.setValue("Value");
		Mockito.when(secondaryKeyValueDAO.getValue(Mockito.anyString())).thenReturn(keyValuePair);
		Mockito.doNothing().when(secondaryKeyValueDAO).save(Mockito.any(KeyValuePair.class));
	    
	}


	private KeyValuePair getMockedKeyValue() {
		KeyValuePair keyValuePair = new KeyValuePair();
		keyValuePair.setKey("Key1");
		keyValuePair.setValue("Value1");
		return keyValuePair;
	}

	@Test
	public void testGetValueByKey() {
		Assert.assertNotNull(service.getValue("key1"));
	}

	@Test
	public void testProcessingNewKeyValuePair() {
		service.processEvent(getMockedKeyValue());
	}
}
