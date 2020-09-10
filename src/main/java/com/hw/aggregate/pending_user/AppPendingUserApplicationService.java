package com.hw.aggregate.pending_user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hw.aggregate.pending_user.command.CreatePendingUserCommand;
import com.hw.aggregate.pending_user.model.PendingUser;
import com.hw.aggregate.pending_user.model.PendingUserQueryRegistry;
import com.hw.aggregate.pending_user.representation.AppPendingUserCardRep;
import com.hw.aggregate.user.AppBizUserApplicationService;
import com.hw.shared.IdGenerator;
import com.hw.shared.idempotent.AppChangeRecordApplicationService;
import com.hw.shared.idempotent.ChangeRepository;
import com.hw.shared.rest.DefaultRoleBasedRestfulService;
import com.hw.shared.rest.VoidTypedClass;
import com.hw.shared.sql.RestfulQueryRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class AppPendingUserApplicationService extends DefaultRoleBasedRestfulService<PendingUser, AppPendingUserCardRep, Void, VoidTypedClass> {
    @Autowired
    private ActivationCodeEmailService activationCodeEmailService;
    @Autowired
    private PendingUserRepo pendingBizUserRepo;
    @Autowired
    private AppBizUserApplicationService bizUserApplicationService;
    @Autowired
    private IdGenerator idGenerator2;
    @Autowired
    private AppChangeRecordApplicationService changeRepository2;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PendingUserQueryRegistry pendingUserQueryRegistry;

    @PostConstruct
    private void setUp() {
        repo = pendingBizUserRepo;
        queryRegistry = pendingUserQueryRegistry;
        entityClass = PendingUser.class;
        role = RestfulQueryRegistry.RoleEnum.APP;
        idGenerator = idGenerator2;
        appChangeRecordApplicationService = changeRepository2;
        om = objectMapper;
    }

    @Override
    public PendingUser replaceEntity(PendingUser pendingBizUser, Object command) {
        return null;
    }

    @Override
    public AppPendingUserCardRep getEntitySumRepresentation(PendingUser pendingBizUser) {
        return new AppPendingUserCardRep(pendingBizUser);
    }

    @Override
    public Void getEntityRepresentation(PendingUser pendingBizUser) {
        return null;
    }

    @Override
    protected PendingUser createEntity(long id, Object command) {
        PendingUser pendingBizUser = PendingUser.create(((CreatePendingUserCommand) command).getEmail(), pendingBizUserRepo, bizUserApplicationService, idGenerator);
        activationCodeEmailService.sendActivationCode(pendingBizUser.getActivationCode(), pendingBizUser.getEmail());
        return pendingBizUser;
    }

    @Override
    public void preDelete(PendingUser pendingUser) {

    }

    @Override
    public void postDelete(PendingUser pendingUser) {

    }

    @Override
    protected void prePatch(PendingUser pendingUser, Map<String, Object> params, VoidTypedClass middleLayer) {

    }

    @Override
    protected void postPatch(PendingUser pendingUser, Map<String, Object> params, VoidTypedClass middleLayer) {

    }


}
