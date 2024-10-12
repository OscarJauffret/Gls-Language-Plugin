package com.gls.glsplugin.language;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static com.gls.glsplugin.language.GillesAnnotator.GILLES_PREFIX_STR;
import static com.gls.glsplugin.language.GillesAnnotator.GILLES_SEPARATOR_STR;

final class GillesReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class),
                new PsiReferenceProvider() {
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element,
                                                                           @NotNull ProcessingContext context) {
                        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                        String value = literalExpression.getValue() instanceof String ?
                                (String) literalExpression.getValue() : null;
                        if ((value != null && value.startsWith(GILLES_PREFIX_STR + GILLES_SEPARATOR_STR))) {
                            TextRange property = new TextRange(GILLES_PREFIX_STR.length() + GILLES_SEPARATOR_STR.length() + 1,
                                    value.length() + 1);
                            return new PsiReference[]{new GillesReference(element, property)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }
}
