package com.mt.identityaccess.domain.service;

import com.mt.common.domain.model.domain_event.DomainEventPublisher;
import com.mt.common.domain.model.restful.PatchCommand;
import com.mt.identityaccess.domain.DomainRegistry;
import com.mt.identityaccess.domain.model.ActivationCode;
import com.mt.identityaccess.domain.model.pending_user.PendingUser;
import com.mt.identityaccess.domain.model.pending_user.RegistrationEmail;
import com.mt.identityaccess.domain.model.user.*;
import com.mt.identityaccess.domain.model.user.event.UserCreated;
import com.mt.identityaccess.domain.model.user.event.UserGetLocked;
import com.mt.identityaccess.domain.model.user.event.UserPasswordChanged;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    public UserId create(UserEmail userEmail, UserPassword password, ActivationCode activationCode, UserId userId) {
        Optional<PendingUser> pendingUser = DomainRegistry.pendingUserRepository().pendingUserOfEmail(new RegistrationEmail(userEmail.getEmail()));
        if (pendingUser.isPresent()) {
            if (pendingUser.get().getActivationCode() == null || !pendingUser.get().getActivationCode().getActivationCode().equals(activationCode.getActivationCode()))
                throw new IllegalArgumentException("activation code mismatch");
            User user = new User(userEmail, password, userId);
            DomainRegistry.userRepository().add(user);
            DomainEventPublisher.instance().publish(new UserCreated(user.getUserId()));
            return user.getUserId();
        } else {
            return null;
        }
    }

    public void updatePassword(User user, CurrentPassword currentPwd, UserPassword password) {
        if (!DomainRegistry.encryptionService().compare(user.getPassword(), currentPwd))
            throw new IllegalArgumentException("wrong password");
        user.setPassword(password);
        DomainRegistry.userRepository().add(user);
        DomainEventPublisher.instance().publish(new UserPasswordChanged(user.getUserId()));
    }

    public void forgetPassword(UserEmail email) {
        Optional<User> user = DomainRegistry.userRepository().searchExistingUserWith(email);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("user does not exist");
        }
        PasswordResetCode passwordResetToken = new PasswordResetCode();
        user.get().setPwdResetToken(passwordResetToken);
        DomainRegistry.userRepository().add(user.get());

    }

    public void resetPassword(UserEmail email, UserPassword newPassword, PasswordResetCode token) {
        Optional<User> user = DomainRegistry.userRepository().searchExistingUserWith(email);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("user does not exist");
        }
        if (user.get().getPwdResetToken() == null)
            throw new IllegalArgumentException("token not exist");
        if (!user.get().getPwdResetToken().equals(token))
            throw new IllegalArgumentException("token mismatch");
        user.get().setPassword(newPassword);
        DomainRegistry.userRepository().add(user.get());
        DomainEventPublisher.instance().publish(new UserPasswordChanged(user.get().getUserId()));
    }

    public void batchLock(List<PatchCommand> commands) {
        if (Boolean.TRUE.equals(commands.get(0).getValue())) {
            commands.stream().map(e -> new UserId(e.getPath().split("/")[1])).forEach(e -> {
                DomainEventPublisher.instance().publish(new UserGetLocked(e));
            });
        }
        DomainRegistry.userRepository().batchLock(commands);
    }
}
