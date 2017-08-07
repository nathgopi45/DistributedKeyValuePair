package com.indix.distributed.keyValue.secondary.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigTest {

  @InjectMocks
  SecondaryKeyValueApplicationConfiguration applicationConfiguration;

  @Test
  public void test() {
    Assert.assertNotNull(applicationConfiguration);
    Assert.assertNull(applicationConfiguration.getKafkaBrokerAdress());
    Assert.assertNull(applicationConfiguration.getKafkaZooKeeperAdress());
    applicationConfiguration.setKafkaBrokerAdress("test");
    applicationConfiguration.setKafkaZooKeeperAdress("test2");
    Assert.assertEquals("test", applicationConfiguration.getKafkaBrokerAdress());
    Assert.assertEquals("test2", applicationConfiguration.getKafkaZooKeeperAdress());
  }

}
