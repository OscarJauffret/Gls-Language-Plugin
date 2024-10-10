package com.gls.glsplugin.language.psi;

import com.gls.glsplugin.language.GillesFileType;
import com.gls.glsplugin.language.GillesLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.FileViewProvider;
import com.intellij.openapi.fileTypes.FileType;

public class GillesFile extends PsiFileBase {

    public GillesFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, GillesLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return GillesFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Gilles";
    }
}
