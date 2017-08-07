package com.indix.distributed.keyValue.secondary.controller;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.indix.distributed.keyValue.common.exception.EventHandlerException;
import com.indix.distributed.keyValue.secondary.service.SecondaryKeyValueService;

@RunWith(MockitoJUnitRunner.class)
public class SecondaryKeyValueControllerTest {

  @InjectMocks
  SecondaryKeyValueController controller;
    
  @Mock
  SecondaryKeyValueService service;

  @Test
  public void shouldCallServiceForAnyValidRequest() throws EventHandlerException {
    controller.secondaryKeyValue((Mockito.any(String.class)));
    verify(service, atLeastOnce()).getValue(Mockito.any(String.class));
  }
  
}
