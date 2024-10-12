package com.gls.glsplugin.language.psi;

import com.gls.glsplugin.language.GillesLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class GillesTokenType extends IElementType {
    public GillesTokenType(@NotNull @NonNls String debugName) {
        super(debugName, GillesLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "GillesTokenType." + super.toString();
    }
}
