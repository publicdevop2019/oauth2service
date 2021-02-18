package com.mt.identityaccess.application.client;

import com.mt.common.query.QueryCriteria;
import com.mt.common.sql.exception.EmptyQueryValueException;
import com.mt.identityaccess.domain.DomainRegistry;
import com.mt.identityaccess.domain.model.client.ClientId;
import com.mt.identityaccess.domain.model.user.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientQuery extends QueryCriteria {

    public ClientQuery(ClientId clientId) {
        super(clientId);
    }

    public static ClientQuery resourceIds(String domainId) {
        return new ClientQuery("resourceIds:" + domainId, true);
    }

    public ClientQuery(String queryParam, boolean isInternal) {
        super(queryParam);
        if (!isInternal)
            validate();
    }

    public ClientQuery(Set<ClientId> resources) {
        super("clientId:" + String.join(".", resources.stream().map(ClientId::getDomainId).collect(Collectors.toSet())));
    }

    public void validate() {
        boolean isRoot = DomainRegistry.authenticationService().isUser()
                && DomainRegistry.authenticationService().userInRole(Role.ROLE_ROOT);
        boolean isUser = DomainRegistry.authenticationService().isUser()
                && DomainRegistry.authenticationService().userInRole(Role.ROLE_USER);
        if (isRoot || isUser) {
            if (rawValue == null) {
                if (!isRoot) {
                    throw new IllegalArgumentException("only root role allows empty query");
                }
            } else {
                String[] queryParams = rawValue.split(",");
                for (String param : queryParams) {
                    String[] split = param.split(":");
                    if (split.length != 2) {
                        throw new EmptyQueryValueException();
                    }
                    //only root user and general user can query, general user can only query by id
                    if (!isRoot) {
                        if (!"id".equals(split[0]))
                            throw new IllegalArgumentException("user role can only query by id");
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("only root/user role allows empty query");
        }
    }
}
