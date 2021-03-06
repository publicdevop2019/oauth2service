package com.mt.identityaccess.domain.service;

import com.mt.common.domain.model.domain_event.DomainEventPublisher;
import com.mt.identityaccess.domain.DomainRegistry;
import com.mt.identityaccess.domain.model.ActivationCode;
import com.mt.identityaccess.domain.model.pending_user.PendingUser;
import com.mt.identityaccess.domain.model.pending_user.RegistrationEmail;
import com.mt.identityaccess.domain.model.pending_user.event.PendingUserCreated;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PendingUserService {

    public RegistrationEmail createOrUpdatePendingUser(
            RegistrationEmail email,
            ActivationCode activationCode
    ) {
        Optional<PendingUser> pendingResourceOwner = DomainRegistry.pendingUserRepository().pendingUserOfEmail(email);
        if (pendingResourceOwner.isEmpty()) {
            PendingUser pendingUser = new PendingUser(email, activationCode);
            DomainRegistry.pendingUserRepository().add(pendingUser);
            DomainEventPublisher.instance().publish(new PendingUserCreated(pendingUser.getRegistrationEmail()));
            return pendingUser.getRegistrationEmail();
        } else {
            pendingResourceOwner.get().newActivationCode(activationCode);
            return pendingResourceOwner.get().getRegistrationEmail();
        }
    }
}
