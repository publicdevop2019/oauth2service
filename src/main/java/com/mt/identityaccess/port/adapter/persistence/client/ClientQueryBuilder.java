package com.mt.identityaccess.port.adapter.persistence.client;

import com.mt.common.sql.builder.SelectQueryBuilder;
import com.mt.common.sql.clause.SelectFieldBooleanEqualClause;
import com.mt.common.sql.clause.SelectFieldStringLikeClause;
import com.mt.identityaccess.domain.model.client.Client;
import org.springframework.stereotype.Component;

import static com.mt.common.CommonConstant.COMMON_ENTITY_ID;

@Component
public class ClientQueryBuilder extends SelectQueryBuilder<Client> {
    public static final String ENTITY_ACCESS_TOKEN_VALIDITY_SECONDS = "accessTokenValiditySeconds";
    public static final String ENTITY_RESOURCE_INDICATOR = "resourceIndicator";
    public static final String ENTITY_CLIENT_ID = "clientId";
    public static final String ENTITY_NAME = "name";
    public static final String ENTITY_GRANT_TYPE_ENUMS = "grantTypeEnums";
    public static final String ENTITY_GRANT_AUTHORITIES = "grantedAuthorities";
    public static final String ENTITY_SCOPE_ENUMS = "scopeEnums";
    public static final String ENTITY_RESOURCE_IDS = "resourceIds";

    {
        sortConverter = new ClientSortConverter();
        supportedWhere.put(COMMON_ENTITY_ID, new SelectFieldDomainIdEqualClause<>("clientId"));
        supportedWhere.put(ENTITY_RESOURCE_INDICATOR, new SelectFieldBooleanEqualClause<>("accessible"));
        supportedWhere.put(ENTITY_CLIENT_ID, new SelectFieldDomainIdEqualClause<>("clientId"));
        supportedWhere.put(ENTITY_NAME, new SelectFieldStringLikeClause<>(ENTITY_NAME));
        supportedWhere.put(ENTITY_GRANT_TYPE_ENUMS, new SelectFieldGrantEnabledBooleanClause());
        supportedWhere.put(ENTITY_GRANT_AUTHORITIES, new SelectFieldStringLikeClause<>("authorities"));
        supportedWhere.put(ENTITY_SCOPE_ENUMS, new SelectFieldStringLikeClause<>(ENTITY_SCOPE_ENUMS));
        supportedWhere.put(ENTITY_RESOURCE_IDS, new SelectFieldResourceIdsClause());
        supportedWhere.put(ENTITY_ACCESS_TOKEN_VALIDITY_SECONDS, new SelectFieldGrantAccessTokenClause());
    }
}
