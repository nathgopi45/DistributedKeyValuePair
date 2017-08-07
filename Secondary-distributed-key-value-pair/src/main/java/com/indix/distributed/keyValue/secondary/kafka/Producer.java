package com.indix.distributed.keyValue.secondary.kafka;


public interface Producer<E extends Event> {

  public String publish(final E event);

}
