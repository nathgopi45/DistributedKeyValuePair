package com.indix.distributed.primary.core.config;



public interface Producer<T,E> {

  public T publish(final E event);

}
