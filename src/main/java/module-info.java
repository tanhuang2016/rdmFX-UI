module rdmFX.UI {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires MaterialFX;
    requires atlantafx.base;
    opens atlantafx.xyz.hashdog.rdm.ui to javafx.graphics, javafx.controls;
    // 新增导出语句
    exports atlantafx.xyz.hashdog.rdm.ui.controller to javafx.fxml;
    exports atlantafx.xyz.hashdog.rdm.ui to javafx.fxml;
    // 新增开放语句
    opens atlantafx.xyz.hashdog.rdm.ui.controller to javafx.fxml;

//    requires atlantafx.base;

    requires java.desktop;
    requires java.prefs;
    requires javafx.swing;
    requires javafx.media;
//    requires javafx.web;
//    requires javafx.fxml;
    requires jdk.zipfs;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.feather;
    requires org.kordamp.ikonli.material2;
    requires org.jetbrains.annotations;

    requires fr.brouillard.oss.cssfx;
    requires datafaker;
    requires com.google.gson;

    exports atlantafx.sampler;
    exports atlantafx.sampler.fake.domain;
    exports atlantafx.sampler.event;
    exports atlantafx.sampler.layout;
    exports atlantafx.sampler.page;
    exports atlantafx.sampler.page.general;
    exports atlantafx.sampler.page.components;
    exports atlantafx.sampler.page.showcase;
    exports atlantafx.sampler.theme;
    exports atlantafx.sampler.util;

//    opens atlantafx.sampler.fake.domain;

    // resources
//    opens atlantafx.sampler;
//    opens atlantafx.sampler.assets.highlightjs;
//    opens atlantafx.sampler.assets.styles;
//    opens atlantafx.sampler.images;
//    opens atlantafx.sampler.images.modena;
//    opens atlantafx.sampler.media;
//    opens atlantafx.sampler.page.general;
//    opens atlantafx.sampler.page.showcase;
}

