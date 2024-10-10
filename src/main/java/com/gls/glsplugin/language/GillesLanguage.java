package com.gls.glsplugin.language;

import com.intellij.lang.Language;

public class GillesLanguage extends Language {

    public static final GillesLanguage INSTANCE = new GillesLanguage();

    private GillesLanguage() {
        super("Gilles");
    }

}