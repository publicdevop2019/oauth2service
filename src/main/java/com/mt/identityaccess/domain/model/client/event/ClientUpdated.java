package com.mt.identityaccess.domain.model.client.event;

import com.mt.identityaccess.domain.model.client.ClientId;

public class ClientUpdated extends ClientEvent{
    public ClientUpdated(ClientId clientId) {
        super(clientId);
    }
}