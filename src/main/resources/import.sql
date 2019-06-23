INSERT INTO resource_owner (ID,Created_at,Email,Password,modified_at,locked,granted_authority,created_by,resource_Id) VALUES(0,CURRENT_TIMESTAMP,'root','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW',CURRENT_TIMESTAMP,false,'ROLE_ROOT,ROLE_ADMIN,ROLE_USER','script','Res_Profile,Res_Album,Res_Payment');
INSERT INTO resource_owner (ID,Created_at,Email,Password,modified_at,locked,granted_authority,created_by,resource_Id) VALUES(1,CURRENT_TIMESTAMP,'user','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW',CURRENT_TIMESTAMP,false,'ROLE_USER','script','Res_Profile,Res_Album');
INSERT INTO resource_owner (ID,Created_at,Email,Password,modified_at,locked,granted_authority,created_by,resource_Id) VALUES(2,CURRENT_TIMESTAMP,'123@gmail.com','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW',CURRENT_TIMESTAMP,false,'ROLE_USER','script','Res_Payment');
INSERT INTO resource_owner (ID,Created_at,Email,Password,modified_at,locked,granted_authority,created_by) VALUES(3,CURRENT_TIMESTAMP,'admin','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW',CURRENT_TIMESTAMP,false,'ROLE_ADMIN,ROLE_USER','script');
INSERT INTO client (ID,Client_Id,Client_Secret,Grant_Type_enums,granted_authority,Scope_enums,access_token_validity_seconds,refresh_token_validity_seconds,has_secret,resource_Ids) VALUES(0,'login-id','$2a$10$KRp4.vK8F8MYLJGEz7im8.71T2.vFQj/rrNLQLOLPEADuv0Gdg.x6','password,refresh_token','ROLE_FRONTEND,ROLE_TRUST','write,read,trust',1800,3600,false,'oauth2-id,myprofile-id,storage-id,payment-id');
INSERT INTO client (ID,Client_Id,Client_Secret,Grant_Type_enums,granted_authority,Scope_enums,access_token_validity_seconds,refresh_token_validity_seconds,has_secret,resource_Ids) VALUES(1,'oauth2-id','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW','client_credentials','ROLE_BACKEND,ROLE_TRUST','write,read,trust',1800,1800,true,'oauth2-id,myprofile-id');
INSERT INTO client (ID,Client_Id,Client_Secret,Grant_Type_enums,granted_authority,Scope_enums,access_token_validity_seconds,refresh_token_validity_seconds,has_secret,resource_Ids) VALUES(2,'register-id','$2a$10$KRp4.vK8F8MYLJGEz7im8.71T2.vFQj/rrNLQLOLPEADuv0Gdg.x6','client_credentials','ROLE_FRONTEND','write',1800,1800,false,'oauth2-id,myprofile-id,storage-id');
INSERT INTO client (ID,Client_Id,Client_Secret,Grant_Type_enums,granted_authority,Scope_enums,access_token_validity_seconds,refresh_token_validity_seconds,registered_Redirect_Uri,has_secret,resource_Ids) VALUES(3,'mgfb-id','$2a$10$KRp4.vK8F8MYLJGEz7im8.71T2.vFQj/rrNLQLOLPEADuv0Gdg.x6','authorization_code','ROLE_FRONTEND,ROLE_THIRD_PARTY','write',1800,1800,'http://localhost:4200',false,'oauth2-id,storage-id');
INSERT INTO client (ID,Client_Id,Client_Secret,Grant_Type_enums,granted_authority,Scope_enums,access_token_validity_seconds,refresh_token_validity_seconds,has_secret) VALUES(4,'test-id','$2a$10$KRp4.vK8F8MYLJGEz7im8.71T2.vFQj/rrNLQLOLPEADuv0Gdg.x6','password','ROLE_FRONTEND,ROLE_TRUST','write,read,trust',1800,3600,false);
INSERT INTO client (ID,Client_Id,Client_Secret,Grant_Type_enums,granted_authority,Scope_enums,access_token_validity_seconds,refresh_token_validity_seconds,has_secret,resource_Ids) VALUES(5,'rightRoleWrongResourceId','$2a$10$KRp4.vK8F8MYLJGEz7im8.71T2.vFQj/rrNLQLOLPEADuv0Gdg.x6','client_credentials','ROLE_FRONTEND','write',1800,1800,false,'oauth2-id,myprofile-id,storage-id');
INSERT INTO client (ID,Client_Id,Client_Secret,Grant_Type_enums,granted_authority,Scope_enums,access_token_validity_seconds,refresh_token_validity_seconds,has_secret) VALUES(6,'rightRoleNoResourceId','$2a$10$KRp4.vK8F8MYLJGEz7im8.71T2.vFQj/rrNLQLOLPEADuv0Gdg.x6','client_credentials','ROLE_FRONTEND','write',1800,1800,false);
INSERT INTO client (ID,Client_Id,Client_Secret,Grant_Type_enums,granted_authority,Scope_enums,access_token_validity_seconds,refresh_token_validity_seconds,registered_Redirect_Uri,has_secret,resource_Ids) VALUES(7,'mgfb-id-backend','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW','authorization_code','ROLE_FRONTEND,ROLE_THIRD_PARTY','write',1800,1800,'http://localhost:4200',false,'oauth2-id,storage-id');
