package com.ryokugyoku.blog.db.repository;

import com.ryokugyoku.blog.db.entity.contents.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Integer> {

    // 並び順を固定して取得
    List<MainCategory> findAllByOrderByIdAsc();
}

