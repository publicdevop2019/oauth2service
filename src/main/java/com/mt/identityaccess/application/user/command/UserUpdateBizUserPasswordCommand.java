package com.mt.identityaccess.application.user.command;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateBizUserPasswordCommand implements Serializable {
    private static final long serialVersionUID = 1;
    private String currentPwd;
    private String password;
}
