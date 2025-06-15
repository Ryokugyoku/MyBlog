package com.ryokugyoku.blog.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * DB を一切使わず、sub が一致したユーザへ ROLE_ADMIN を追加する
 */
@Service
@RequiredArgsConstructor
public class CustomAdminOidcUserService extends OidcUserService {

    private final AdminSubjectProperties adminProps;

    @Override
    public OidcUser loadUser(OidcUserRequest request) {

        OidcUser raw = super.loadUser(request);

        Set<GrantedAuthority> authorities = new HashSet<>(raw.getAuthorities());

        if (adminProps.getAdminSubjects().contains(raw.getSubject())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        // 表示名を email とした例
        return new DefaultOidcUser(
                authorities,
                raw.getIdToken(),
                raw.getUserInfo(),
                "email");
    }
}
