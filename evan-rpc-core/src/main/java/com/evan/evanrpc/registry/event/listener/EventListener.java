package com.evan.evanrpc.registry.event.listener;

import com.evan.evanrpc.registry.RegistryServiceCache;

public interface EventListener {
    void doEvent(RegistryServiceCache registryServiceCache);
}
