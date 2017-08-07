package com.indix.distributed.common.kafka;

import com.indix.distributed.common.model.Event;

public interface Producer<E extends Event> {

  public String publish(final E event);

}
