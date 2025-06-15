package com.ryokugyoku.blog.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * security.admin-subjects に列挙した OIDC "sub" を保持
 */
@Data
@Component
@ConfigurationProperties(prefix = "security")
public class AdminSubjectProperties {

    /**
     * 管理者として扱う subject 値
     */
    private Set<String> adminSubjects = new HashSet<>();
}
