package com.mt.identityaccess.domain.model.client;

import com.mt.common.domain.model.domainId.DomainId;
import com.mt.identityaccess.domain.DomainRegistry;

import java.io.Serializable;

public class ClientId extends DomainId implements Serializable {

    public ClientId() {
        super();
        Long id = DomainRegistry.uniqueIdGeneratorService().id();
        String s = Long.toString(id, 36);
        setDomainId("0C" + s.toUpperCase());
    }

    public ClientId(String domainId) {
        super(domainId);
    }

}
