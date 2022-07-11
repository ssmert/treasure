package com.jakers.mustneed.core.auth.application;

import com.jakers.mustneed.core.auth.api.dto.SignInRequest;
import com.jakers.mustneed.core.auth.api.dto.SignInResponse;
import com.jakers.mustneed.core.auth.api.dto.SignUpRequest;
import com.jakers.mustneed.core.enums.Errors;
import com.jakers.mustneed.core.enums.Roles;
import com.jakers.mustneed.core.exception.BaseDuplicateException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${custom.keycloak.login-url}")
    String loginUrl;

    @Value("${keycloak.resource}")
    String clientId;

    @Value("${keycloak.credentials.secret}")
    String clientSecret;

    @Value("${keycloak.realm}")
    private String realm;

    private final Keycloak keycloak;

    /**
     * 회원가입
     *
     * @param request 요청 데이터
     * @return 계정 아이디
     */
    public String signUp(SignUpRequest request) {
        // 아이디 중복 체크
        if (isExistUser(request.getEmailId())) {
            throw new BaseDuplicateException(Errors.DUPLICATED_USER);
        }
        // 계정 설정
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(request.getEmailId());
        user.setEmail(request.getEmailId());

        // 비밀번호 설정
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(request.getPw());
        user.setCredentials(List.of(passwordCred));

        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();

        // 사용자 생성
        Response response = usersResource.create(user);
        String userId = CreatedResponseUtil.getCreatedId(response);

        // 역할 할당
        ClientRepresentation clientRep = realmResource.clients().findByClientId(clientId)
            .get(0);
        UserResource userResource = usersResource.get(userId);
        RoleRepresentation clientRoleRep = realmResource.clients().get(clientRep.getId())
            .roles().get(Roles.USER.getCode())
            .toRepresentation();

        userResource.roles().clientLevel(clientRep.getId())
            .add(Collections.singletonList(clientRoleRep));

        return userId;
    }

    /**
     * 로그인
     *
     * @param request 요청 데이터
     * @return 로그인 응답데이터
     */
    public SignInResponse signIn(SignInRequest request) {

        Configuration configuration = new Configuration(serverUrl, realm, clientId,
            new HashMap<>() {{
                put("secret", clientSecret);
                put("grant_type", "password");
            }}, null);
        AuthzClient authzClient = AuthzClient.create(configuration);

        AccessTokenResponse response = authzClient.obtainAccessToken(request.getEmailId(),
            request.getPw());

        return new SignInResponse(response.getToken(), response.getRefreshToken());
    }

    /**
     * 아이디로 사용자 조회
     *
     * @param userName 아이디
     * @return 키클락 사용자 정보
     */
    private Optional<UserRepresentation> search(String userName) {
        return keycloak.realm(realm).users().search(userName).stream().findFirst();
    }

    /**
     * 아이디로 사용자 존재여부 조회
     *
     * @param userName 아이디
     * @return 존재여부
     */
    public boolean isExistUser(String userName) {
        return search(userName).isPresent();
    }

}
