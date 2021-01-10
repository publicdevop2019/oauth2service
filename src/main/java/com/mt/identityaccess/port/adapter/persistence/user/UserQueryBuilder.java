package com.mt.identityaccess.port.adapter.persistence.user;

import com.mt.common.sql.builder.SelectQueryBuilder;
import com.mt.common.sql.clause.SelectFieldBooleanEqualClause;
import com.mt.common.sql.clause.SelectFieldStringLikeClause;
import com.mt.identityaccess.domain.model.user.User;
import org.springframework.stereotype.Component;

import static com.mt.identityaccess.domain.model.user.User.*;

@Component
public class UserQueryBuilder extends SelectQueryBuilder<User> {
    {
        DEFAULT_PAGE_SIZE = 20;
        MAX_PAGE_SIZE = 50;
        mappedSortBy.put(ENTITY_EMAIL, ENTITY_EMAIL);
        mappedSortBy.put("createdAt", "createdAt");
        supportedWhereField.put(ENTITY_EMAIL, new SelectFieldUserEmailClause());
        supportedWhereField.put(ENTITY_SUBSCRIPTION, new SelectFieldBooleanEqualClause<>(ENTITY_SUBSCRIPTION));
        supportedWhereField.put(ENTITY_GRANTED_AUTHORITIES, new SelectFieldStringLikeClause<>(ENTITY_GRANTED_AUTHORITIES));
        allowEmptyClause = true;
    }

}