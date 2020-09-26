package com.bp.hmi.citytour.action;

/**
 * BindingConsumer.
 */
public interface BindingConsumer<T> {
    void call(T t);
}
