package com.gls.glsplugin.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class GillesFileType extends LanguageFileType {

    public static final GillesFileType INSTANCE = new GillesFileType();

    private GillesFileType() {
        super(GillesLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Gilles";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Gilles language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "gls";
    }

    @Override
    public Icon getIcon() {
        return GillesIcons.FILE;
    }

}