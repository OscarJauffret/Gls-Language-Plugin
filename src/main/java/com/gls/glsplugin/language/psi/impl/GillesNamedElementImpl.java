package com.gls.glsplugin.language.psi.impl;

import com.gls.glsplugin.language.psi.GillesNamedElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class GillesNamedElementImpl extends ASTWrapperPsiElement implements GillesNamedElement {
    public GillesNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
