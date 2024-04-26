package com.evan.evanrpc.registry.event;

import com.evan.evanrpc.registry.RegistryServiceCache;
import com.evan.evanrpc.registry.event.listener.EventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    public enum EventType {
        PUT, DELETE
    }

    public Map<Enum<EventType>, List<EventListener>> listeners = new HashMap<>();

    /**
     * 订阅, 增加了一个监听对象
     * @param eventType
     * @param listener
     */
    public void subscribe(Enum<EventType> eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);
        eventListeners.add(listener);
    }

    /**
     * 取消订阅, 取消对一个对象的监听
     * @param eventType
     * @param listener
     */
    public void unsubscribe(Enum<EventType> eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);
        eventListeners.remove(listener);
    }

    /**
     * 通知监听对象做自己的事情
     * @param eventType
     * @param registryServiceCache
     */
    public void notify(Enum<EventType> eventType, RegistryServiceCache registryServiceCache) {
        List<EventListener> eventListeners = listeners.get(eventType);
        for (EventListener eventListener : eventListeners) {
            eventListener.doEvent(registryServiceCache);
        }
    }
}
