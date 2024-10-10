package com.gls.glsplugin.language.psi;

import com.gls.glsplugin.language.GillesLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class GillesElementType extends IElementType {

    public GillesElementType(@NotNull @NonNls String debugName) {
        super(debugName, GillesLanguage.INSTANCE);
    }
}
