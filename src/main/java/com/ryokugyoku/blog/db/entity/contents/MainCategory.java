package com.ryokugyoku.blog.db.entity.contents;

import jakarta.persistence.*;
import lombok.*;
/**
 * サイドメニューに表示される項目
 * @author Ryokugyoku
 * */
@Entity
@Table(name = "main_category")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MainCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nameEn;

    @Column(nullable = false)
    private String nameJp;


    @Column(nullable = false)
    private String nameEs;

    /* ---------- 追加：ロケール毎に動的設定されるフィールド ---------- */
    @Transient                // DB へは保存しない
    private String name;      // 画面表示用


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameJp() {
        return nameJp;
    }

    public void setNameJp(String nameJp) {
        this.nameJp = nameJp;
    }

    public String getNameEs() {
        return nameEs;
    }

    public void setNameEs(String nameEs) {
        this.nameEs = nameEs;
    }
}
