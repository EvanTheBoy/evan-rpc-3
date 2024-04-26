package com.evan.evanrpc.registry.event.listener;

import com.evan.evanrpc.registry.RegistryServiceCache;

public class DeleteEventListener implements EventListener {
    @Override
    public void doEvent(RegistryServiceCache registryServiceCache) {
        registryServiceCache.clearCache();
    }
}
