package com.mt.identityaccess.infrastructure;

import com.mt.identityaccess.domain.model.client.ClientId;
import com.mt.identityaccess.domain.model.ticket.SignedTicket;
import com.mt.identityaccess.domain.model.ticket.TicketInfo;
import com.mt.identityaccess.domain.model.user.UserId;
import com.mt.identityaccess.domain.service.TicketService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.Date;

import static com.mt.identityaccess.domain.model.ticket.TicketInfo.CLIENT_ID;
import static com.mt.identityaccess.domain.model.ticket.TicketInfo.USER_ID;

@Slf4j
@Service
public class JwtTicketService implements TicketService {
    @Autowired
    JwtInfoProviderService jwtInfoProviderService;

    @Override
    public SignedTicket create(UserId userId, ClientId clientId) {
        TicketInfo ticket = TicketInfo.create(userId, clientId);
        return encrypt(ticket);
    }

    private SignedTicket encrypt(TicketInfo ticket) {
        JWKSet publicKeys = jwtInfoProviderService.getPublicKeys();
        KeyPair keyPair = jwtInfoProviderService.getKeyPair();

        JWK jwk = publicKeys.getKeys().get(0);

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(jwk.getKeyID()).build(),
                new JWTClaimsSet.Builder()
                        .subject("ticket")
                        .issueTime(new Date())
                        .expirationTime(new Date(ticket.getExp()))
                        .claim(USER_ID, ticket.getUserId().getDomainId())
                        .claim(CLIENT_ID, ticket.getClientId().getDomainId())
                        .build());

        try {
            signedJWT.sign(new RSASSASigner(keyPair.getPrivate()));
        } catch (JOSEException e) {
            log.error("error during generating ticket", e);
        }
        return new SignedTicket(signedJWT.serialize());
    }
}
