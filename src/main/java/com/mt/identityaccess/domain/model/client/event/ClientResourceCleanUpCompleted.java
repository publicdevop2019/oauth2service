package com.mt.identityaccess.domain.model.client.event;

import com.mt.identityaccess.domain.model.client.ClientId;

import java.util.Set;

public class ClientResourceCleanUpCompleted extends ClientEvent {
    public ClientResourceCleanUpCompleted(Set<ClientId> pendingRevoked) {
        super(pendingRevoked);
    }
}
