package com.gls.glsplugin.language;

import com.gls.glsplugin.language.psi.GillesAssignBlock;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

final class GillesReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private final String key;

    GillesReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<GillesAssignBlock> properties = GillesUtil.findProperties(project, key);
        List<ResolveResult> results = new ArrayList<>();
        for (GillesAssignBlock property : properties) {
            results.add(new PsiElementResolveResult(property));
        }
        return results.toArray(new ResolveResult[0]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @Override
    public Object @NotNull [] getVariants() {
        Project project = myElement.getProject();
        List<GillesAssignBlock> properties = GillesUtil.findProperties(project);
        List<LookupElement> variants = new ArrayList<>();
        for (final GillesAssignBlock property : properties) {
            if (property.getKey() != null && !property.getKey().isEmpty()) {
                variants.add(LookupElementBuilder
                        .create(property).withIcon(GillesIcons.FILE)
                        .withTypeText(property.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}
