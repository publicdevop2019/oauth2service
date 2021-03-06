package com.mt.identityaccess.domain.model.user;

import com.google.common.base.Objects;
import com.mt.common.domain.model.validate.Validator;
import com.mt.identityaccess.domain.DomainRegistry;
import lombok.Getter;

public class PasswordResetCode {
    @Getter
    private String value;

    public PasswordResetCode() {
        setValue(DomainRegistry.passwordResetTokenService().generate());
    }

    public PasswordResetCode(String value) {
        setValue(value);
    }

    private void setValue(String value) {
        Validator.lengthGreaterThanOrEqualTo(value, 9);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasswordResetCode)) return false;
        PasswordResetCode that = (PasswordResetCode) o;
        return Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
