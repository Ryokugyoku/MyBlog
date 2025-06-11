package com.ryokugyoku.blog.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * セッションにロケールが存在しないときだけ
 * Accept-Language をデフォルト値として採用する Resolver
 */
public class HeaderAwareSessionLocaleResolver extends SessionLocaleResolver {

    /** Accept-Language が空の場合のフォールバック */
    private static final Locale FALLBACK = Locale.JAPAN;

    @Override
    protected Locale determineDefaultLocale(HttpServletRequest request) {
        // ブラウザの最優先ロケールを取得
        Locale headerLocale = request.getLocale();  // null になることはほぼ無い
        return headerLocale != null ? headerLocale : FALLBACK;
    }
}