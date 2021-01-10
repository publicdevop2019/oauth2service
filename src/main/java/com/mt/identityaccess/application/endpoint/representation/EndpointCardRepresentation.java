package com.mt.identityaccess.application.endpoint.representation;

import com.mt.identityaccess.domain.model.endpoint.Endpoint;
import lombok.Data;

@Data
public class EndpointCardRepresentation {
    private String id;
    private String expression;
    private String description;
    private String resourceId;
    private String path;
    private String method;
    private Integer version;

    public EndpointCardRepresentation(Object o) {
        Endpoint endpoint = (Endpoint) o;
        this.id = endpoint.getEndpointId().getDomainId();
        this.expression = endpoint.getExpression();
        this.description = endpoint.getDescription();
        this.resourceId = endpoint.getClientId().getDomainId();
        this.path = endpoint.getPath();
        this.method = endpoint.getMethod();
        this.version = endpoint.getVersion();
    }
}