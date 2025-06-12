package com.ryokugyoku.blog.web;

import com.ryokugyoku.blog.service.LocalizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes {
    private final LocalizeService localizeService;

    /** すべてのビューで利用できる言語タグ一覧をモデルに追加 */
    @ModelAttribute("supportedLangTags")
    public List<String> supportedLangTags() {
        List<String> langTags = localizeService.getSupportedLanguageTags();
        return langTags;
    }

}
