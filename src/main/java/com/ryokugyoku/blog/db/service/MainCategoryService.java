package com.ryokugyoku.blog.db.service;

import com.ryokugyoku.blog.db.entity.contents.MainCategory;
import com.ryokugyoku.blog.db.repository.MainCategoryRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 *
 *
 * */
@Service
@RequiredArgsConstructor
public class MainCategoryService {

    private final MainCategoryRepository repository;

    @Transactional
    public List<MainCategory> getAll() {
        return repository.findAllByOrderByIdAsc();
    }

    @Transactional(readOnly = true)
    public List<MainCategory> getAllForSidebar(){
        List<MainCategory> categories = getAll();
        Locale locale = LocaleContextHolder.getLocale();
        String lang = locale.getLanguage();

        for (MainCategory c : categories) {
            switch (lang) {
                case "ja" -> c.setName(c.getNameJp());
                case "es" -> c.setName(c.getNameEs());
                default   -> c.setName(c.getNameEn());
            }
        }
        return categories;
    }

    /**
     * メインカテゴリを新規登録する
     *
     * @param nameJp 日本語名
     * @param nameEn 英語名
     * @param nameEs スペイン語名
     * @return 保存後のエンティティ（ID 付き）
     */
    @Transactional
    public MainCategory register(String nameJp, String nameEn, String nameEs) {
        MainCategory entity = MainCategory.builder()
                .nameJp(nameJp)
                .nameEn(nameEn)
                .nameEs(nameEs)
                .build();
        return repository.save(entity);
    }
}
