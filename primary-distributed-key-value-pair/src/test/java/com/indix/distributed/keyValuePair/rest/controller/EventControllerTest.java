package com.indix.distributed.keyValuePair.rest.controller;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;

import com.indix.distributed.common.model.events.DistributingEvent;
import com.indix.distributed.primary.core.exception.KafkaConnectException;
import com.indix.distributed.primary.core.service.impl.EventStoreServiceImpl;
import com.indix.distributed.primary.rest.controller.EventController;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {

  @InjectMocks
  private EventController controller;

  @Mock
  private EventStoreServiceImpl service;
  
  @Mock
  HttpServletResponse httpServletResponse;
  
  private MockMvc mockMVC;

  @Before
  public void init() {
    when(service.process(Mockito.any(DistributingEvent.class))).thenReturn(null);
  }
 

  @Test(expected = HttpMessageNotReadableException.class)
  public void shouldCallExceptionHandlerForMessageNotReadable() throws IOException, KafkaConnectException {
    doThrow(new HttpMessageNotReadableException("message not readable")).when(service)
        .process(Mockito.any(DistributingEvent.class));
    controller
        .createGenericEvent(
            "{\"eventDateTime\":\"2016-12-02T15:30:30\",\"eventType\":\"LogisticUnitScheduled\","
                + "\"vehicleTripId\":\"vehtripid02\",\"geolocation\":{\"latitude\":213121232131.12,\"longitude\":1221213.23},\"trackingId\":\"trackingId1\"}",
            createHeaderMap());
    
  }
  
  @Test
  public void shouldCallServiceForValidEventRequest() throws IOException, KafkaConnectException {
    controller
        .createGenericEvent(
            "{\"eventDateTime\":\"2016-12-02T15:30:30\",\"eventType\":\"LogisticUnitScheduled\","
                + "\"vehicleTripId\":\"vehtripid02\",\"geolocation\":{\"latitude\":213121232131.12,\"longitude\":1221213.23},\"trackingId\":\"trackingId1\"}",
            createHeaderMap());
    verify(service, atLeastOnce()).process(Mockito.any(DistributingEvent.class));
  }

  /*
   * @Test public void shouldReturnEventOncreateEventDocumentCall(){
   * assertEquals(documentFactory.createDocument(createVehicleTripEvent()),
   * createVehicleTripEvent()); }
   */

  private Map<String, String> createHeaderMap() {
    Map<String, String> map = new HashMap<>();
    map.put("Content-Type", "application/json");
    map.put("Authorization", "Bearer 88769cee-b38a-41dd-a4a1-80cc57c2aeea");
    map.put("Event-Category", "LogisticUnitScheduled");
    return map;
  }
  

  


}
