INSERT INTO resource_owner (ID,Created_at,Email,Password,modified_at,locked,granted_authority,created_by) VALUES(0,CURRENT_TIMESTAMP,'root','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW',CURRENT_TIMESTAMP,false,'ROLE_ROOT','script');
INSERT INTO resource_owner (ID,Created_at,Email,Password,modified_at,locked,granted_authority,created_by) VALUES(1,CURRENT_TIMESTAMP,'user','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW',CURRENT_TIMESTAMP,false,'ROLE_USER','script');
INSERT INTO resource_owner (ID,Created_at,Email,Password,modified_at,locked,granted_authority,created_by) VALUES(2,CURRENT_TIMESTAMP,'123@gmail.com','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW',CURRENT_TIMESTAMP,false,'ROLE_USER','script');
INSERT INTO oauth_client (ID,Client_Id,Client_Secret,Authorized_Grant_Types,granted_authority,Scope,access_token_validity_seconds,refresh_token_validity_seconds) VALUES(0,'login-id','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW','password,refresh_token','ROLE_FRONTEND,ROLE_TRUST','write,read,trust',1800,3600);
INSERT INTO oauth_client (ID,Client_Id,Client_Secret,Authorized_Grant_Types,granted_authority,Scope,access_token_validity_seconds,refresh_token_validity_seconds) VALUES(1,'oauth2-id','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW','client_credentials','ROLE_BACKEND,ROLE_TRUST','write,read,trust',1800,1800);
INSERT INTO oauth_client (ID,Client_Id,Client_Secret,Authorized_Grant_Types,granted_authority,Scope,access_token_validity_seconds,refresh_token_validity_seconds) VALUES(2,'register-id','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW','client_credentials','ROLE_FRONTEND','write',1800,1800);
INSERT INTO oauth_client (ID,Client_Id,Client_Secret,Authorized_Grant_Types,granted_authority,Scope,access_token_validity_seconds,refresh_token_validity_seconds,registered_Redirect_Uri) VALUES(3,'mgfb-id','$2a$10$AWv./gLENRDp7ghJbawG1uSYa0Qlf4xwaaMW8LHwcEOBdVOB6UPZW','authorization_code','ROLE_FRONTEND','write',1800,1800,'http://localhost:4200');