package com.mt.identityaccess.application.client.representation;

import com.mt.identityaccess.application.user.representation.UserSpringRepresentation;
import com.mt.identityaccess.domain.model.client.*;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
public class ClientSpringOAuth2Representation implements ClientDetails {
    private ClientId clientId;
    private String clientSecret;
    private Set<GrantType> grantTypeEnums;
    private Set<Role> grantedAuthorities;
    private Set<Scope> scopeEnums;
    private int accessTokenValiditySeconds;
    private Set<String> registeredRedirectUri;
    private int refreshTokenValiditySeconds;
    private Set<String> resourceIds;
    private boolean autoApprove = false;

    public ClientSpringOAuth2Representation(Client client) {
        setClientId(client.getClientId());
        setClientSecret(client.getSecret());
        setGrantTypeEnums(client.totalGrantTypes());
        setGrantedAuthorities(client.getRoles());
        setScopeEnums(client.getScopes());
        setAccessTokenValiditySeconds(client.accessTokenValiditySeconds());
        if(client.getPasswordGrant()!=null&&client.getPasswordGrant().getRefreshTokenGrant()!=null){
            setRefreshTokenValiditySeconds(client.getPasswordGrant().getRefreshTokenGrant().getRefreshTokenValiditySeconds());
        }
        setResourceIds(client.getResources().stream().map(ClientId::getDomainId).collect(Collectors.toSet()));
        if (client.getAuthorizationCodeGrant() != null)
            setRegisteredRedirectUri(client.getAuthorizationCodeGrant().getRedirectUrls().stream().map(RedirectURL::getValue).collect(Collectors.toSet()));
    }

    @Override
    public String getClientId() {
        return clientId.getDomainId();
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return scopeEnums.stream().map(Enum::name).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return grantTypeEnums.stream().map(e -> e.name().toLowerCase()).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities.stream().map(UserSpringRepresentation.GrantedAuthorityImpl::new).collect(Collectors.toList());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }


}
