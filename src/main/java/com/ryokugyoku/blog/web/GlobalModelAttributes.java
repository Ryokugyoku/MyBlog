package com.ryokugyoku.blog.web;

import com.ryokugyoku.blog.db.entity.contents.MainCategory;
import com.ryokugyoku.blog.db.service.MainCategoryService;
import com.ryokugyoku.blog.service.LocalizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes {
    private final LocalizeService localizeService;

    /**
     * フッターの言語切り替え処理
     * */
    @ModelAttribute("supportedLangTags")
    public List<String> supportedLangTags() {
        return localizeService.getSupportedLanguageTags();
    }

    /**
     * サイドバー項目用を取得するためのサービス
     * */
    private final MainCategoryService mainCategoryService;

    /**
     * サイドバー用カテゴリーリスト
     */
    @ModelAttribute("mainCategories")
    public List<MainCategory> mainCategories() {
        return mainCategoryService.getAllForSidebar();
    }


}
