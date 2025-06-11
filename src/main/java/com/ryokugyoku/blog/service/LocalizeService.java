package com.ryokugyoku.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LocalizeService {
    /** messages[_xx[_YY]].properties にマッチさせる正規表現 */
    private static final Pattern MSG_FILE =
            Pattern.compile("messages(?:_([a-z]{2,3})(?:_([A-Z]{2}|[a-z]{2}))?)?\\.properties");

    /**
     * クラスパス上の messages*.properties から対応ロケール一覧を取得する
     *
     * @return 例: [ja, en, es, ]  ※basename だけのファイルは Locale.ROOT
     */
    public List<Locale> getSupportedLocales() {
        Resource[] resources;
        try {
            resources = new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:messages*.properties");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        Set<Locale> detected = new HashSet<>();

        for (Resource res : resources) {
            String name = Optional.ofNullable(res.getFilename()).orElse("");
            Matcher m = MSG_FILE.matcher(name);
            if (m.matches()) {
                String lang = m.group(1);    // null → basename だけ
                String country = m.group(2);

                Locale locale = (lang == null)
                        ? Locale.ROOT
                        : (country != null ? new Locale(lang, country) : new Locale(lang));
                detected.add(locale);
            }
        }

        return detected.stream()
                .sorted(Comparator.comparing(Locale::toString))
                .toList();
    }

    /**
     * ISO 文字列 (ja, en-US など) の一覧が欲しい場合はこちらを利用
     */
    public List<String> getSupportedLanguageTags() {
        return getSupportedLocales().stream()
                .map(l -> l == Locale.ROOT ? "" : l.toLanguageTag())
                .toList();
    }


}
