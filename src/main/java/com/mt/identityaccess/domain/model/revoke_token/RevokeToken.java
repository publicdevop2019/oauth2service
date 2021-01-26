package com.mt.identityaccess.domain.model.revoke_token;

import com.google.common.base.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Date;

@Data
@Slf4j
@NoArgsConstructor
public class RevokeToken {
    public static final String ENTITY_TARGET_ID = "targetId";
    public static final String ENTITY_ISSUE_AT = "issuedAt";
    private String targetId;
    private Long issuedAt;
    private RevokeTokenId revokeTokenId;
    private TokenType type;

    public RevokeToken(String targetId, RevokeTokenId revokeTokenId, TokenType type) {
        this.targetId = targetId;
        this.issuedAt = Instant.now().getEpochSecond();
        this.revokeTokenId = revokeTokenId;
        this.type = type;
    }

    public enum TokenType {
        CLIENT,
        USER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RevokeToken)) return false;
        RevokeToken token = (RevokeToken) o;
        return Objects.equal(revokeTokenId, token.revokeTokenId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(revokeTokenId);
    }
}
